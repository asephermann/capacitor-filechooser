package io.github.asephermann.plugins.filechooser

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.result.ActivityResult
import com.getcapacitor.JSObject
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.ActivityCallback
import com.getcapacitor.annotation.CapacitorPlugin
import java.io.File
import java.lang.String


private const val CHOOSE_FILE_REQUEST = "chooseFileResult"
private const val TAG = "FileChooser"
const val MIME = "mime"

@CapacitorPlugin(name = "FileChooser")
class FileChooserPlugin : Plugin() {

    private val implementation = FileChooser()

    @PluginMethod
    fun open(call: PluginCall) {
        chooseFile(call)
    }

    @PluginMethod
    fun chooseFile(call: PluginCall) {
        val filter = call.getString(MIME)
        val uriFilter = if (!filter.equals("")) filter else "*/*"

        // type and title should be configurable
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = uriFilter
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        intent.flags = Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        intent.flags = Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION

        val chooser = Intent.createChooser(intent, "Select File")

        // Start the Activity for result using the name of the callback method
        startActivityForResult(call, chooser, CHOOSE_FILE_REQUEST)
    }

    @ActivityCallback
    private fun chooseFileResult(call: PluginCall, result: ActivityResult) {
        val file : File?
        var path = ""
        var messages = ""

        when (result.resultCode) {
            Activity.RESULT_OK -> {
                val uri: Uri? = result.data?.data
                if (uri != null) {
                    file = File(String.valueOf(uri))
                    Log.w(TAG, uri.toString())
                    path = implementation.makeFileCopyInCacheDirectory(activity, uri)
                    messages = "OK"
                } else {
                    file = null
                    path = ""
                    messages = "File uri was null"
                }
            }
            Activity.RESULT_CANCELED ->{
                file = null
                path = ""
                messages = "User canceled."
            }
            else -> {
                file = null
                path = ""
                messages = result.resultCode.toString()
            }
        }

        val ret = JSObject()
        ret.put("file", file)
        ret.put("path", path)
        ret.put("messages", messages)
        call.resolve(ret)
    }
}