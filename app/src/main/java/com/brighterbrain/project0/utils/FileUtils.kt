package com.brighterbrain.project0.utils

import android.content.Context
import android.os.Environment
import android.os.Environment.DIRECTORY_PICTURES
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class FileUtils{
    companion object {
        @Throws(IOException::class)
        fun createImageFile(context: Context): File {
            // Create an image file name
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
            val imageFileName = "JPEG_" + timeStamp + "_"
            val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val image = File.createTempFile(
                    imageFileName, /* prefix */
                    ".jpg", /* suffix */
                    storageDir      /* directory */
            )
            return image
        }
    }
}