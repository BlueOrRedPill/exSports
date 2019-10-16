package com.android.sportnmedc

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.sportnmedc.helpers.ToolbarHelper
import kotlinx.android.synthetic.main.activity_send_result.*

class SendResultActivityEx:ExBaseActivity(R.layout.activity_send_result),ToolbarHelper{


    override fun hasBackMenu(): Boolean =false
    override fun toolbarTitle(): String? = "Smart Pay Out"


    companion object {
        fun newIntent(mActivity: AppCompatActivity, address:String, amount:String): Intent {
            val intent = Intent(mActivity,SendResultActivityEx::class.java)
            intent.putExtra("address",address)
            intent.putExtra("amount",amount)
            return intent
        }
    }

    override fun initMenuEvent(mToolbar: Toolbar?) {

    }
    override fun onActivityCreated() {
        setupToolbar()
        var amount = "0"
        intent.extras?.let {
             amount = it.getString("amount","0")
            val  address = it.getString("address","")
            amount_txt.text = "$amount EXS"
            address_txt.text = address
            total_amount_txt.text = "$amount EXS"
        }
        setResult(Activity.RESULT_OK)
        mToolbar?.leftMenuImageEvent(0, View.OnClickListener {

        })
        mToolbar?.rightMenuImageEvent(R.drawable.ic_close, View.OnClickListener {
            finish()
        })

        transaction_detail_btn.setOnClickListener {
           startActivity(TransactionDetailActivityEx.newIntent(mActivity,amount))
        }



    }




}