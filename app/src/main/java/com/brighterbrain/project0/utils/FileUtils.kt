package com.brighterbrain.project0.utils

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import android.os.Environment.DIRECTORY_PICTURES
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import android.provider.MediaStore




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

        fun getRealPathFromURI(context: Context, contentUri: Uri): String {
            var cursor: Cursor? = null
            try {
                val projection = arrayOf(MediaStore.Images.Media.DATA)
                cursor = context.contentResolver.query(contentUri, projection, null, null, null)
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