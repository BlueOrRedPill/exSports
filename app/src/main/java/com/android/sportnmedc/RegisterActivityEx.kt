package com.android.sportnmedc

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.sportnmedc.helpers.ToolbarHelper
import com.android.sportnmedc.network.model.UserModel
import com.android.sportnmedc.ui.BaseActivity
import com.android.sportnmedc.viewmodels.LoginViewModel
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.confirm_btn
import java.io.File

class RegisterActivityEx:BaseActivity<LoginViewModel>(R.layout.activity_register),ToolbarHelper{

    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? = ""

    companion object {
        fun newIntent(mActivity: AppCompatActivity): Intent {
            val intent = Intent(mActivity,RegisterActivityEx::class.java)
            return intent
        }
    }

    override fun initViewModel(): LoginViewModel {
        return  ViewModelProviders.of(this).get(LoginViewModel::class.java).also {
            it.user().observe(this, mSuccess)
            it.loadingIndicator().observe(this, mLoading)
            it.error().observe(this, mError)
        }
    }

    override fun initMenuEvent(mToolbar: Toolbar?) {

    }
    override fun onActivityCreated() {
        setupToolbar()
        confirm_btn.setOnClickListener {
            mViewModel.registerAction(email_edt.text.toString(), name_edt.text.toString(), "", register_password_edt.text.toString(), confirm_password_edt.text.toString(), "123456", imageFile)
//            finish()
        }

        image_view.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_PICK
            startActivityForResult(intent, 1001)
        }
    }

    private var imageFile: File? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Logger.d("requestCode : $requestCode")
        Logger.d("resultCode : $resultCode")
        val file = File(data?.data?.path)

        Logger.d(file.absolutePath)
        Logger.d(data?.data.toString())
        Logger.d(data?.data?.let { getRealPathFromURI(contentResolver, it) })

        imageFile = File(data?.data?.let { getRealPathFromURI(contentResolver, it) })
        image_view.setImageURI(data?.data)
    }

    private fun getRealPathFromURI(contentResolver: ContentResolver, contentURI: Uri): String? {
        val result: String?
        val cursor = contentResolver.query(contentURI, null, null, null, null)
        if (cursor == null) {
            result = contentURI.path
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }

    private val mSuccess = Observer<UserModel> {
        Toast.makeText(mActivity,"Hello : ${it.name}", Toast.LENGTH_SHORT).show()
        var data = Intent()
        data.putExtra("email", email_edt.text.toString())
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    private val mError = Observer<String> {
        Toast.makeText(mActivity,it, Toast.LENGTH_SHORT).show()
    }

    private val mLoading = Observer<Boolean> {
        if (it){
            confirm_btn.isEnabled = false
            register_content_loading.show()
        }else{
            confirm_btn.isEnabled = true
            register_content_loading.hide()
        }
    }
}