package com.brighterbrain.project0.utils

import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationSettingsRequest

class CommonUtils {
    companion object {
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
