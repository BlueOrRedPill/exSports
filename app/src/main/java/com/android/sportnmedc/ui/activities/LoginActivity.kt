package com.android.sportnmedc.ui.activities

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.sportnmedc.BaseApplication
import com.android.sportnmedc.HomeActivityEx
import com.android.sportnmedc.R
import com.android.sportnmedc.RegisterActivityEx
import com.android.sportnmedc.helpers.ToolbarHelper
import com.android.sportnmedc.helpers.Utils
import com.android.sportnmedc.network.model.UserModel
import com.android.sportnmedc.network.response.ResLibraryItemTransactionList
import com.android.sportnmedc.ui.BaseActivity
import com.android.sportnmedc.viewmodels.LoginViewModel
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity: BaseActivity<LoginViewModel>(R.layout.activity_login),ToolbarHelper{

    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? = ""

    companion object {
        fun newIntent(mActivity: AppCompatActivity): Intent {
            return Intent(mActivity, LoginActivity::class.java)
        }
    }

    override fun initMenuEvent(mToolbar: Toolbar?) {

    }

    override fun initViewModel(): LoginViewModel {
        return  ViewModelProviders.of(this).get(LoginViewModel::class.java).also {
            it.user().observe(this, mSuccess)
            it.loadingIndicator().observe(this, mLoading)
            it.error().observe(this, mError)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == Utils.REQUEST_CODE_REGISTER && resultCode == Activity.RESULT_OK) {
            username_edt.setText(data?.getStringExtra("email"))
        }
    }

    override fun onActivityCreated() {
        setupToolbar()
//        username_edt.setText("dbsehdgh21@gmail.com")
//        password_edt.setText("1111")

        confirm_btn.setOnClickListener {
            mViewModel.loginAction(username_edt.text.toString(),password_edt.text.toString())
        }
        register_btn.setOnClickListener {
            startActivityForResult(RegisterActivityEx.newIntent(mActivity), Utils.REQUEST_CODE_REGISTER)
        }

        //TEST CODE *************
//        val assetManager = resources.assets
//        val inputStream= assetManager.open("test.json")
//        val jsonString = inputStream.bufferedReader().use { it.readText() }
//
//        Logger.json(jsonString)
//
//        val test = ResLibraryItemTransactionList.fromJson(jsonString)
//        Logger.json(test.toString())
//        Logger.json(test.result.toString())
//        Logger.json(test.result.data.toString())
//        Logger.json(test.data.toString())


    }


    private val mSuccess = Observer<UserModel> {
        if(BaseApplication.prefs.uId == 0L) {
            BaseApplication.prefs.uId = it.uId
            mViewModel.userProfile(it.uId)
        } else {
            BaseApplication.prefs.publicKey = it.publicKey
            BaseApplication.prefs.profile = it.toString()
            Toast.makeText(mActivity,"Hello : ${it.name}", Toast.LENGTH_SHORT).show()
            startActivity(HomeActivityEx.newIntent(mActivity))
            finish()
        }
    }

    private val mError = Observer<String> {
        Toast.makeText(mActivity,it, Toast.LENGTH_SHORT).show()
    }

    private val mLoading = Observer<Boolean> {
        if (it){
            confirm_btn.isEnabled = false
            content_loading.show()
        }else{
            confirm_btn.isEnabled = true
            content_loading.hide()
        }
    }






}