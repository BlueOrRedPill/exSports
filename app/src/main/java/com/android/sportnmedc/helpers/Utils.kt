package com.android.sportnmedc.helpers

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast


object Utils{
     const val REQUEST_CODE_BUYSELL = 1
     const val REQUEST_CODE_TRADE = 2

     const val REQUEST_CODE_ADD_COIN = 3
     const val REQUEST_CODE_REGISTER = 4

     const val REQUEST_CODE_READ_EXTERNAL_STORAGE = 101


     fun copyToClipboard(context:Context,text:String){
          try {
               val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
               val clip = ClipData.newPlainText(text, text)
               clipboard!!.setPrimaryClip(clip)
               Toast.makeText(context,"Copied success",Toast.LENGTH_SHORT).show()
          }catch (e:Exception){
               Toast.makeText(context,"Copied fail",Toast.LENGTH_SHORT).show()
          }

     }

     fun hideKeyboard(activity: Activity) {
          val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
          //Find the currently focused view, so we can grab the correct window token from it.
          var view = activity.currentFocus
          //If no view currently has focus, create a new one, just so we can grab a window token from it
          if (view == null) {
               view = View(activity)
          }
          imm.hideSoftInputFromWindow(view.windowToken, 0)
     }

     fun getExtension(fileStr: String): String {
          return fileStr.substring(fileStr.lastIndexOf(".") + 1, fileStr.length)
     }
}