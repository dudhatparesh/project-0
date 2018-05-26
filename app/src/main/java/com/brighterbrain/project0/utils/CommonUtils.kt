package com.brighterbrain.project0.utils

import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationSettingsRequest

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
    }
}
