package com.android.sportnmedc

import android.content.Intent
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.sportnmedc.helpers.ToolbarHelper
import com.android.sportnmedc.helpers.Utils
import com.android.sportnmedc.network.model.PaymentModel
import com.android.sportnmedc.ui.BaseActivity
import com.android.sportnmedc.viewmodels.SendConfirmActivityViewModel
import kotlinx.android.synthetic.main.activity_send_confirm.*

class SendConfirmActivityEx :BaseActivity<SendConfirmActivityViewModel>(R.layout.activity_send_confirm), ToolbarHelper {
    override fun initViewModel(): SendConfirmActivityViewModel {
        return  ViewModelProviders.of(this).get(SendConfirmActivityViewModel::class.java).also {
            it.payment().observe(this, mSuccess)
            it.loadingIndicator().observe(this, mLoading)
            it.error().observe(this, mError)
        }
    }

    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? ="Confirmation"
    data class Item(val key:String,val value:String)

    val data =  ArrayList<Item>()


//
//    data class GroupView(val header:View,val content:View,val icon:ImageView,var isShow:Boolean)
//    val arrGroupView = arrayListOf<GroupView>()

    companion object {
        fun newIntent(mActivity: AppCompatActivity, address:String, amount:String): Intent {
            val intent = Intent(mActivity,SendConfirmActivityEx::class.java)
            intent.putExtra("address",address)
            intent.putExtra("amount",amount)
            return intent
        }
    }

    override fun initMenuEvent(mToolbar: Toolbar?) {

    }

    var amount = "0"
    var address = ""

    override fun onActivityCreated() {
        setupToolbar()
        intent.extras?.let {
            amount = it.getString("amount","0")
            address = it.getString("address","")
            data.add(Item("RECEIVEMENT'S ADDRESS",address  ))
            data.add(Item("AMOUNT TO RECEIVE",amount+" EXS"))
            amount_txt.text = amount+" EXS"
            total_amount_txt.text = amount+" EXS"
        }



        if (confirm_container.childCount > 0) confirm_container.removeAllViews()
        for (i in 0 until data.size){
            val v = LayoutInflater.from(mActivity).inflate(R.layout.item_transaction_detail,confirm_container,false)
            val keyTxt: TextView = v.findViewById(R.id.key)
            val valueTxt: TextView = v.findViewById(R.id.value)
            data[i].let { item->
                keyTxt.text = item.key
                valueTxt.text = item.value
            }
            confirm_container.addView(v)
        }

        confirm_txt.text = "Withdraw $amount EXS"
        confirm_btn.setOnClickListener {
            mViewModel.sendCoin(address, amount.toInt())
        }

    }


    private val mSuccess = Observer<PaymentModel> {
        startActivityForResult(SendResultActivityEx.newIntent(mActivity,address, amount),Utils.REQUEST_CODE_ADD_COIN)
    }

    private val mError = Observer<String> {
        Toast.makeText(mActivity,it, Toast.LENGTH_SHORT).show()
    }

    private val mLoading = Observer<Boolean> {
        if (it) {
            confirm_btn.isEnabled = false
            content_loading.show()
        } else {
            confirm_btn.isEnabled = true
            content_loading.hide()
        }
    }
}