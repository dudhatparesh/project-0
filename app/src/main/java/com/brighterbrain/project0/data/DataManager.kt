package com.brighterbrain.project0.data

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.support.v4.app.ActivityCompat
import com.brighterbrain.project0.data.model.Item
import com.brighterbrain.project0.di.ActivityContext
import com.brighterbrain.project0.di.ApplicationContext
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Single
import io.reactivex.SingleObserver
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(@ApplicationContext private var appContext: Context,
                                      private var databaseHelper: DatabaseHelper,
                                      private var prefHelper: PrefHelper,
                                      private var firebaseHelper: FirebaseHelper) {
    fun addItem(item: Item):Completable{
        return object:Completable(){
            override fun subscribeActual(s: CompletableObserver?) {
                try {
                    val id = databaseHelper.appDatabase.itemDao().insert(item)
                    firebaseHelper.firebaseDatabase.getReference("project-0")
                            .child("items")
                            .child(id.toString())
                            .setValue(item)
                    s?.onComplete()
                }catch (e: Exception){
                    s?.onError(e)
                }
            }
        }
    }
    fun getItems(): Single<List<Item>> {
        return object :Single<List<Item>>(){
            override fun subscribeActual(observer: SingleObserver<in List<Item>>) {
                try{
                    observer.onSuccess(databaseHelper.appDatabase.itemDao().getAllItems())
                }catch (e:Exception){
                    observer.onError(e)
                }
            }
        }
    }
    fun hasPermissions(perms: Array<String>):Boolean{
        return EasyPermissions.hasPermissions(appContext, *perms)
    }

    fun shouldAskPermission(perms: Array<String> , activityContext: Activity): Boolean {
        perms.forEach {
            if(!prefHelper.get(it,"true").toBoolean() &&
                    !ActivityCompat.shouldShowRequestPermissionRationale(activityContext,it)){
                return false
            }
        }
        return true
    }

    fun setPermissionAskedFirstTime(perms: Array<String>, value: Boolean) {
        perms.forEach {
            prefHelper.set(it,value.toString())
        }
    }
}