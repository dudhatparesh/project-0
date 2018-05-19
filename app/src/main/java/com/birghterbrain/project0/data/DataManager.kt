package com.birghterbrain.project0.data

import android.content.Context
import com.birghterbrain.project0.data.model.Item
import com.birghterbrain.project0.di.ApplicationContext
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Single
import io.reactivex.SingleObserver
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(@ApplicationContext context: Context,
                                      private var databaseHelper: DatabaseHelper) {
    fun addItem(item: Item):Completable{
        return object:Completable(){
            override fun subscribeActual(s: CompletableObserver?) {
                try {
                    databaseHelper.appDatabase.itemDao().insertAll(listOf(item))
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
}