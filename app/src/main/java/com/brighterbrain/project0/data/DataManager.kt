package com.brighterbrain.project0.data

import android.app.Activity
import android.content.Context
import android.support.v4.app.ActivityCompat
import com.brighterbrain.project0.data.model.Item
import com.brighterbrain.project0.data.network.RestApiHelper
import com.brighterbrain.project0.di.ApplicationContext
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Single
import io.reactivex.SingleObserver
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
open class DataManager @Inject constructor(@ApplicationContext private var appContext: Context,
                                           private var databaseHelper: DatabaseHelper,
                                           private var prefHelper: PrefHelper,
                                           private var restApiHelper: RestApiHelper) {
    fun saveItem(item: Item, filePath: String?): Completable {
        return object : Completable() {
            override fun subscribeActual(s: CompletableObserver?) {
                try {
                    val response = restApiHelper.saveItem(item, filePath).execute()
                    if (response.isSuccessful) {
                        if (response.body()!!.status == 200) {
                            if (item.id == null) {
                                databaseHelper.addItem(response.body()!!.item!!)
                            } else {
                                databaseHelper.updateItem(response.body()!!.item!!)
                            }
                            s?.onComplete()
                        } else {
                            s?.onError(RuntimeException(response.body()!!.message))
                        }
                    } else {
                        s?.onError(RuntimeException(response.errorBody()!!.string()))
                    }
                } catch (e: Exception) {
                    s?.onError(e)
                }
            }
        }
    }

    fun deleteItem(id: Long): Completable {
        return object : Completable() {
            override fun subscribeActual(s: CompletableObserver?) {
                try {
                    val response = restApiHelper.deleteItem(id).execute()
                    if (response.isSuccessful) {
                        if (response.body()!!.status == 200) {
                            databaseHelper.deleteItem(id)
                            s?.onComplete()
                        } else {
                            s?.onError(RuntimeException(response.body()!!.message))
                        }
                    } else {
                        s?.onError(RuntimeException(response.errorBody()!!.string()))
                    }
                } catch (e: Exception) {
                    s?.onError(e)
                }
            }

        }
    }

    fun getItems(): Single<List<Item>> {
        return object : Single<List<Item>>() {
            override fun subscribeActual(observer: SingleObserver<in List<Item>>) {
                try {
                    val response = restApiHelper.getItems().execute()
                    if (response.isSuccessful && response.body()!!.status == 200) {
                        databaseHelper.refillData(response.body()!!.items)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                observer.onSuccess(databaseHelper.getAllItems())
            }

        }

    }

    fun hasPermissions(perms: Array<String>): Boolean {
        return EasyPermissions.hasPermissions(appContext, *perms)
    }

    fun shouldAskPermission(perms: Array<String>, activityContext: Activity): Boolean {
        perms.forEach {
            if (!prefHelper.get(it, "true").toBoolean() &&
                    !ActivityCompat.shouldShowRequestPermissionRationale(activityContext, it)) {
                return false
            }
        }
        return true
    }

    fun setPermissionAskedFirstTime(perms: Array<String>, value: Boolean) {
        perms.forEach {
            prefHelper.set(it, value.toString())
        }
    }

    fun getItem(id:Long): Single<Item> {
        return object : Single<Item>() {
            override fun subscribeActual(observer: SingleObserver<in Item>) {
                try {
                    val item = databaseHelper.getItem(id)
                    if(item != null){
                        observer.onSuccess(item)
                    }else{
                        observer.onError(RuntimeException("No Item Found"))
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    observer.onError(e)
                }
            }

        }

    }
}