package com.android.sportnmedc.helpers

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.sportnmedc.R


/**
 * Created by AndroidEB on 10/30/2017.
 */
object DialogHelper{

    fun hiddenkeyboard(context:Context,view:View?){
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.let {
            if (view != null) {
                it.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }

    }

    private fun createDialog(mContext: Context, title: String, message: String): AlertDialog.Builder {
        return AlertDialog.Builder(mContext, R.style.DialogAnimation)
            .setTitle(title)
            .setMessage(message)
    }

    fun createLogoutDialog(mActivity:AppCompatActivity,logoutCallBack:LogoutCallBack):AlertDialog{
        val mBuilder = createDialog(mActivity, mActivity.getString(R.string.app_name), "Do you want to logout?")
            .setCancelable(false)
            .setNegativeButton(android.R.string.cancel,null)
            .setPositiveButton(android.R.string.ok) { _, _ -> logoutCallBack.onLogoutConfirm() }
        return mBuilder.create()
    }

    fun createConfirmDialog(mActivity:AppCompatActivity,title:String, message: String, onOkClicked:DialogInterface.OnClickListener):AlertDialog{
        val mBuilder = createDialog(mActivity,title, message)
            .setCancelable(false)
            .setNegativeButton(android.R.string.cancel,null)
            .setPositiveButton(android.R.string.ok,onOkClicked)
        return mBuilder.create()
    }
//
//    fun showInternetErrorDialog(mActivity:AppCompatActivity,internetHandlerCallBack: InternetDialogCallBack):AlertDialog{
//        val mBuilder = createDialog(mActivity, mActivity.getString(R.string.app_name), mActivity.getString(R.string.error_connection_message))
//                .setCancelable(false)
//                .setPositiveButton(R.string.retry, { _, _ -> internetHandlerCallBack.onServiceRetryClicked() })
//                .setOnKeyListener(DialogInterface.OnKeyListener { _, keyCode, _ ->
//                    if (keyCode == KeyEvent.KEYCODE_BACK) {
//                        (mActivity as Activity).onBackPressed()
//                        return@OnKeyListener true
//                    }
//                    false
//                })
//        return mBuilder.create()
//    }

//     fun showFullScreenImage(mActivity:AppCompatActivity,imageUrl: String) {
//        val view = LayoutInflater.from(mActivity).inflate(R.layout.full_screen_image, null)
//        val dialog = Dialog(mActivity, android.R.style.Theme_NoTitleBar)
//        //            dialog.setCancelable(false);
//        val imgDisplay:ImageView = view.findViewById(R.id.imgDisplay)
//        val imgDisplayClose:ImageView = view.findViewById(R.id.imgDisplay_close)
//        ImageHelper.loadImage(mActivity, imageUrl, imgDisplay, 0)
//        imgDisplayClose.setOnClickListener { dialog.dismiss() }
//        dialog.setContentView(view)
//        dialog.show()
//    }
//    fun showDialogPromotion(mActivity:AppCompatActivity,imageUrl: String,link:String) {
//        val view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_promotion, null)
//        val dialog = Dialog(mActivity, android.R.style.Theme_NoTitleBar)
//        dialog.getWindow().setBackgroundDrawable(ColorDrawable(Color.parseColor("#88000000")))
//        //            dialog.setCancelable(false);
//        val imgDisplay:ImageView = view.findViewById(R.id.imgDisplay)
//        if (!link.isNullOrEmpty()) {
//            imgDisplay.setOnClickListener {
//                val i = Intent(Intent.ACTION_VIEW)
//                i.data = Uri.parse(link)
//                startActivity(mActivity, i, null)
//            }
//        }
//        val imgDisplayClose:ImageView = view.findViewById(R.id.imgDisplay_close)
//        ImageHelper.loadImage(mActivity, imageUrl, imgDisplay, 0)
//        imgDisplayClose.setOnClickListener { dialog.dismiss() }
//        dialog.setContentView(view)
//        dialog.show()
//    }

    interface LogoutCallBack{
        fun onLogoutConfirm()
    }

    abstract class InternetDialogCallBack {
        abstract fun onServiceRetryClicked()
    }

//    fun createEditTextDialog(mActivity: AppCompatActivity,title: String,viewValue:TextView,inputType:Int,onClicked:(String)->Unit){
//        val alertDialog =  AlertDialog.Builder(mActivity)
//        alertDialog.setTitle(title)
//        val view =LayoutInflater.from(mActivity).inflate(R.layout.input_view,null)
//        val textInputEditText: TextInputEditText =view.findViewById(R.id.input_editText)
//        textInputEditText.inputType = inputType
//        textInputEditText.imeOptions = EditorInfo.IME_ACTION_DONE
//        textInputEditText.setText(viewValue.text)
//        textInputEditText.hint = title
//        alertDialog.setNegativeButton(android.R.string.cancel,null)
//        alertDialog.setPositiveButton(android.R.string.ok) { _, _ ->
//            viewValue.text = textInputEditText.text
//            onClicked(textInputEditText.text.toString())
//            DialogHelper.hiddenkeyboard(mActivity,view)
//
//        }
//        alertDialog.setView(view)
//        alertDialog.create().show()
//    }
//    fun createChangePasswordDialog(mActivity: AppCompatActivity,title: String,onClicked:(String,String)->Unit){
//        val alertDialogBuilder =  AlertDialog.Builder(mActivity)
//        alertDialogBuilder.setTitle(title)
//        val view =LayoutInflater.from(mActivity).inflate(R.layout.change_password_dialog,null)
//        val oldpassEdt: TextInputEditText =view.findViewById(R.id.oldpass_edt)
//        val newpassEdt: TextInputEditText =view.findViewById(R.id.newpass_edt)
//        val confirmNewpassEdt: TextInputEditText =view.findViewById(R.id.confirm_newpass_edt)
//        alertDialogBuilder.setNegativeButton("CANCEL",null)
//        alertDialogBuilder.setPositiveButton("OK", null)
//        alertDialogBuilder.setView(view)
//        val alertDialog = alertDialogBuilder.create()
//        alertDialog.setOnShowListener{ _ ->
//            val b = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
//            b.setOnClickListener {
//                val oldpass = oldpassEdt.text.toString()
//                val newpass = newpassEdt.text.toString()
//                val confirmNewpass = confirmNewpassEdt.text.toString()
//                if (oldpass.isNullOrEmpty() || newpass.isNullOrEmpty() || confirmNewpass.isNullOrEmpty()){
//                    Utils.showToast(mActivity,"Please input all field.")
//                }else if (!newpass.equals(confirmNewpass)){
//                    Utils.showToast(mActivity,"New password not match.")
//                }else {
//                    DialogHelper.createConfirmDialog(mActivity,"Change password","Do you want change your password?",object: DialogInterface.OnClickListener{
//                        override fun onClick(dialog: DialogInterface?, which: Int) {
//                            onClicked(oldpass, newpass)
//                            DialogHelper.hiddenkeyboard(mActivity, view)
//                            alertDialog?.dismiss()
//                        }
//                    }).show()
//                }
//            }
//        }
//
//        alertDialog.show()
//    }


    fun createSelectDialog(mActivity: AppCompatActivity,key:String,text:String , onClicked:()->Unit){
        val view = LayoutInflater.from(mActivity).inflate(R.layout.select_dialog,null)
        val buy_btn:TextView =   view.findViewById(R.id.buy_btn)
        val detail_btn:TextView =   view.findViewById(R.id.detail_btn)
        buy_btn.setText(text)
        val alertDialogBuilder =  AlertDialog.Builder(mActivity,R.style.DialogAnimation)
        alertDialogBuilder.setView(view)
        val  alertDialog = alertDialogBuilder.create()
        buy_btn.setOnClickListener {
            alertDialog.dismiss()
            when(key){
                "buy_item" ->{
                    createProfileConfirmDialog(mActivity,onClicked)
                }
                "sell_item" ->{
                    createSellConfirmDialog(mActivity,onClicked)
                }
                "cancel_item" ->{
                    createMessageConfirmDialog(mActivity,onClicked)
                }
            }

        }
        detail_btn.setOnClickListener {
            alertDialog.dismiss()
            createDetailDialog(mActivity,onClicked)
        }
        alertDialog.show()
    }
    fun createSellConfirmDialog(mActivity: AppCompatActivity, onClicked:()->Unit){


        val alertDialogBuilder =  AlertDialog.Builder(mActivity,R.style.DialogAnimation)
        val view = LayoutInflater.from(mActivity).inflate(R.layout.activity_sell_card,null)
        val cancelBtn:TextView =   view.findViewById(R.id.cancel_btn)
        val confirmBtn:TextView =   view.findViewById(R.id.confirm_btn)
        alertDialogBuilder.setView(view)

        val  alertDialog = alertDialogBuilder.create()
        cancelBtn.setOnClickListener {
            alertDialog.dismiss()
        }
        confirmBtn.setOnClickListener {
            alertDialog.dismiss()
            onClicked()
        }
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        alertDialog.show()
    }

    fun createProfileConfirmDialog(mActivity: AppCompatActivity, onClicked:()->Unit){
        val view = LayoutInflater.from(mActivity).inflate(R.layout.profile_confirm_dialog,null)
        val cancelBtn:TextView =   view.findViewById(R.id.cancel_btn)
        val confirmBtn:TextView =   view.findViewById(R.id.confirm_btn)

        val alertDialogBuilder =  AlertDialog.Builder(mActivity,R.style.DialogAnimation)
        alertDialogBuilder.setView(view)
        val  alertDialog = alertDialogBuilder.create()
        cancelBtn.setOnClickListener {
            alertDialog.dismiss()
        }
        confirmBtn.setOnClickListener {
            alertDialog.dismiss()
            onClicked()
        }
        alertDialog.show()
    }
    fun createMessageConfirmDialog(mActivity: AppCompatActivity, onClicked:()->Unit){
        val view = LayoutInflater.from(mActivity).inflate(R.layout.message_confirm_dialog,null)
        val cancelBtn:TextView =   view.findViewById(R.id.cancel_btn)
        val confirmBtn:TextView =   view.findViewById(R.id.confirm_btn)

        val alertDialogBuilder =  AlertDialog.Builder(mActivity,R.style.DialogAnimation)
        alertDialogBuilder.setView(view)
        val  alertDialog = alertDialogBuilder.create()
        cancelBtn.setOnClickListener {
            alertDialog.dismiss()
        }
        confirmBtn.setOnClickListener {
            alertDialog.dismiss()
            onClicked()
        }
        alertDialog.show()
    }

    fun createDetailDialog(mActivity: AppCompatActivity, onClicked:()->Unit){
        val view = LayoutInflater.from(mActivity).inflate(R.layout.detail_dialog,null)
        val alertDialogBuilder =  AlertDialog.Builder(mActivity,R.style.DialogAnimation)
        alertDialogBuilder.setView(view)
        val  alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }



}