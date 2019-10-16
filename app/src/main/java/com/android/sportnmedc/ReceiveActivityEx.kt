package com.android.sportnmedc

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.sportnmedc.helpers.ToolbarHelper
import com.android.sportnmedc.helpers.Utils
import com.android.sportnmedc.network.model.PaymentModel
import com.android.sportnmedc.network.model.UserModel
import com.android.sportnmedc.ui.BaseActivity
import com.android.sportnmedc.viewmodels.ReceiveActivityViewModel
import kotlinx.android.synthetic.main.activity_receive.*

class ReceiveActivityEx :BaseActivity<ReceiveActivityViewModel>(R.layout.activity_receive), ToolbarHelper {

    var profile = UserModel.fromJson(BaseApplication.prefs.profile)

    override fun initViewModel(): ReceiveActivityViewModel {
        return  ViewModelProviders.of(this).get(ReceiveActivityViewModel::class.java).also {
            it.payment().observe(this, mSuccess)
            it.loadingIndicator().observe(this, mLoading)
            it.error().observe(this, mError)
        }
    }

    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? ="Receive"
    companion object {
        fun newIntent(mActivity: AppCompatActivity): Intent {
            val intent = Intent(mActivity,ReceiveActivityEx::class.java)
            return intent
        }
    }

    override fun initMenuEvent(mToolbar: Toolbar?) {

    }

    override fun onActivityCreated() {
        setupToolbar()
        buy_with_credit_btn.setOnClickListener {
            startActivityForResult(PaymentActivityEx.newIntent(mActivity), Utils.REQUEST_CODE_ADD_COIN)
        }


        user_id.setOnClickListener {
            Utils.copyToClipboard(mActivity,user_id.text.toString())
        }
        copy_clipboard.setOnClickListener {
            Utils.copyToClipboard(mActivity,copy_clipboard.text.toString())
        }

        profile.let {
            copy_clipboard.text = it.publicKey
            user_id.text = it.uId.toString()
        }

    }

    override fun onResume() {
        super.onResume()
        mViewModel.getBalanceOf()
    }

    private val mSuccess = Observer<PaymentModel> {
        balance_txt.text = "${it.exsBalance} EXS"
        usd_txt.text = "${it.exsBalance} USD"
    }

    private val mError = Observer<String> {
        Toast.makeText(mActivity,it, Toast.LENGTH_SHORT).show()
    }

    private val mLoading = Observer<Boolean> {
//        if (it){
//            confirm_btn.isEnabled = false
//            content_loading.show()
//        }else{
//            confirm_btn.isEnabled = true
//            content_loading.hide()
//        }
    }
}