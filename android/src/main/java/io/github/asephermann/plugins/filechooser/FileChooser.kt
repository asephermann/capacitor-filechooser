package io.github.asephermann.plugins.filechooser

import android.app.Activity
import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class FileChooser {

    fun makeFileCopyInCacheDirectory(activity: Activity, contentUri: Uri): String {
        val returnCursor: Cursor?
        return try {
            val filePathColumn = arrayOf(
                MediaStore.Files.FileColumns._ID,
                MediaStore.Files.FileColumns.TITLE,
                MediaStore.Files.FileColumns.DATA,
                MediaStore.Files.FileColumns.SIZE,
                MediaStore.Files.FileColumns.DATE_ADDED,
                MediaStore.Files.FileColumns.DISPLAY_NAME,
                MediaStore.MediaColumns.DATA,
                MediaStore.MediaColumns.MIME_TYPE,
                MediaStore.MediaColumns.DISPLAY_NAME
            )
            val contentResolver: ContentResolver = activity.contentResolver
            returnCursor = contentResolver.query(contentUri, filePathColumn, null, null, null)
            if (returnCursor != null) {
                returnCursor.moveToFirst()
                val nameIndex: Int =
                    returnCursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME)
                val name: String = returnCursor.getString(nameIndex)
                val file = File(activity.cacheDir, name)
                val inputStream: InputStream? = contentResolver.openInputStream(contentUri)
                val outputStream = FileOutputStream(file)
                val maxBufferSize = 1 * 1024 * 1024
                val bytesAvailable: Int = inputStream!!.available()

                //int bufferSize = 1024;
                val bufferSize = bytesAvailable.coerceAtMost(maxBufferSize)
                val buffers = intToByteArray(bufferSize)
                var length: Int
                while (inputStream.read(buffers).also { length = it } != -1) {
                        outputStream.write(buffers, 0, length)
                    }
                inputStream.close()
                outputStream.close()
                return file.absolutePath
            }
            ""
        } catch (e: Exception) {
            ""
        }
    }

    private fun intToByteArray(value: Int): ByteArray {
        return byteArrayOf(
            (value ushr 24).toByte(),
            (value ushr 16).toByte(),
            (value ushr 8).toByte(),
            value.toByte()
        )
    }
}