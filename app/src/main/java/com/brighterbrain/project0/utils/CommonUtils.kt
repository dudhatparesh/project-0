package com.brighterbrain.project0.utils

import android.content.Context
import android.database.Cursor
import android.net.Uri
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationSettingsRequest
import android.provider.MediaStore



class CommonUtils {
    companion object {
        const val _BASE_URL="http://173.255.210.56:8080"
        const val _IMAGE_URLS= "$_BASE_URL/images/"

        fun getLocationRequest(): LocationRequest {
            val mLocationRequest = LocationRequest()
            mLocationRequest.interval = 10000
            mLocationRequest.fastestInterval = 5000
            mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            return mLocationRequest
        }

        fun getLocationSettingBuilder(): LocationSettingsRequest {

            return LocationSettingsRequest.Builder()
                    .addLocationRequest(getLocationRequest()).build()
        }

        fun getRealPathFromURI(context: Context, contentUri: Uri): String {
            var cursor: Cursor? = null
            try {
                val proj = arrayOf(MediaStore.Images.Media.DATA)
                cursor = context.contentResolver.query(contentUri, proj, null, null, null)
                val columnIndex = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                cursor.moveToFirst()
                return cursor.getString(columnIndex)
            } finally {
                if (cursor != null) {
                    cursor.close()
                }
            }
        }
    }
}
