package com.android.sportnmedc

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.sportnmedc.helpers.ToolbarHelper
import com.android.sportnmedc.helpers.Utils
import kotlinx.android.synthetic.main.activity_send_detail.*

class SendDetailActivityEx :ExBaseActivity(R.layout.activity_send_detail), ToolbarHelper {


    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? ="EXS address"

    var amount = ""
//
//    data class GroupView(val header:View,val content:View,val icon:ImageView,var isShow:Boolean)
//    val arrGroupView = arrayListOf<GroupView>()

    companion object {
        fun newIntent(mActivity: AppCompatActivity,address:String): Intent {
            val intent = Intent(mActivity,SendDetailActivityEx::class.java)
            intent.putExtra("address",address)
            return intent
        }
    }

    override fun initMenuEvent(mToolbar: Toolbar?) {

    }

    override fun onActivityCreated() {
        setupToolbar()
       val address = intent.getStringExtra("address")?:""
        mToolbar?.setToolbarSubTitle(address)
        amount_txt.addTextChangedListener(object:TextWatcher{
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
               Toast.makeText(mActivity,"Please enter amount.",Toast.LENGTH_SHORT).show()
           }else{
               startActivityForResult(SendConfirmActivityEx.newIntent(mActivity,address,amount),Utils.REQUEST_CODE_ADD_COIN)
           }

        }

    }



}