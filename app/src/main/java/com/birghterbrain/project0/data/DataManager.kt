package com.birghterbrain.project0.data

import android.content.Context
import com.birghterbrain.project0.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(@ApplicationContext context: Context,
                                      private var databaseHelper: DatabaseHelper) {

}