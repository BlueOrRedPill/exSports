package com.android.sportnmedc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.sportnmedc.helpers.ToolbarHelper
import com.android.sportnmedc.helpers.Utils
import kotlinx.android.synthetic.main.activity_add_coin.*

class AddCoinActivityEx:ExBaseActivity(R.layout.activity_add_coin),ToolbarHelper{



    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? ="Add EXS"


    companion object {
        fun newIntent(mActivity: AppCompatActivity): Intent {
            val intent = Intent(mActivity,AddCoinActivityEx::class.java)
            return intent
        }
    }
    override fun initMenuEvent(mToolbar: Toolbar?) {

    }

    override fun onActivityCreated() {
        setupToolbar()
        coin_btn.setOnClickListener {
            startActivityForResult(PaymentActivityEx.newIntent(mActivity),Utils.REQUEST_CODE_ADD_COIN)
        }
    }





}