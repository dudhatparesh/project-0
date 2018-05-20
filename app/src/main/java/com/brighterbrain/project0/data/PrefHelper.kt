package com.brighterbrain.project0.data

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefHelper @Inject constructor(var sharedPreferences: SharedPreferences){
    fun get( key:String,defaultValue: String): String{
        return sharedPreferences.getString(key,defaultValue)
    }
    fun set( key: String, value: String): Boolean {
        val editor = sharedPreferences.edit()
        editor.putString(key,value)
        return editor.commit()
    }
}