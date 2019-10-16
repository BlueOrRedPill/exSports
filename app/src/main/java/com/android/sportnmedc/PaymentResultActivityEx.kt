package com.android.sportnmedc

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.sportnmedc.helpers.ToolbarHelper
import kotlinx.android.synthetic.main.activity_payment.confirm_btn
import kotlinx.android.synthetic.main.activity_payment_result.*

class PaymentResultActivityEx:ExBaseActivity(R.layout.activity_payment_result),ToolbarHelper{



    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? =""


    companion object {
        fun newIntent(mActivity: AppCompatActivity,amount:String, exsBalance:String): Intent {
            val intent = Intent(mActivity,PaymentResultActivityEx::class.java)
            intent.putExtra("amount",amount)
            intent.putExtra("exsBalance", exsBalance)
            return intent
        }
    }
    override fun initMenuEvent(mToolbar: Toolbar?) {

    }

    override fun onActivityCreated() {
        setupToolbar()
        intent.getStringExtra("amount")?.let {
            message_txt.text = "$it EXS has been added to your wallet."
        }

        intent.getStringExtra("exsBalance")?.let {
            amount_txt.text = it
        }

        confirm_btn.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }





}