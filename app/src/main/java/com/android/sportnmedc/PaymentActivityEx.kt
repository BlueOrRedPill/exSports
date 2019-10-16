package com.android.sportnmedc

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.sportnmedc.helpers.ToolbarHelper
import com.android.sportnmedc.helpers.Utils
import com.android.sportnmedc.network.model.PaymentModel
import com.android.sportnmedc.ui.BaseActivity
import com.android.sportnmedc.viewmodels.PaymentActivityViewModel
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivityEx:BaseActivity<PaymentActivityViewModel>(R.layout.activity_payment),ToolbarHelper{
    override fun initViewModel(): PaymentActivityViewModel {
        return  ViewModelProviders.of(this).get(PaymentActivityViewModel::class.java).also {
            it.buyCoin().observe(this, mSuccess)
            it.loadingIndicator().observe(this, mLoading)
            it.error().observe(this, mError)
        }
    }


    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? ="Buy with Credit Card"

    var amount = "0"
    companion object {
        fun newIntent(mActivity: AppCompatActivity): Intent {
            val intent = Intent(mActivity,PaymentActivityEx::class.java)
            return intent
        }
    }
    override fun initMenuEvent(mToolbar: Toolbar?) {

    }

    override fun onActivityCreated() {
        setupToolbar()
        price_edt.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                amount = p0?.trim().toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
        confirm_btn.setOnClickListener {
            while (amount.startsWith("0")){
                amount = amount.removePrefix("0")
            }
            if (amount.isNullOrEmpty() || amount.equals("0")){
                Toast.makeText(mActivity,"Please enter amount.", Toast.LENGTH_SHORT).show()
            }else{
                mViewModel.buyCoin(amount.toInt())
            }

        }
    }

    private val mSuccess = Observer<PaymentModel> {
        startActivityForResult(PaymentResultActivityEx.newIntent(mActivity, it.amount.toString(), it.exsBalance.toString()), Utils.REQUEST_CODE_ADD_COIN)
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