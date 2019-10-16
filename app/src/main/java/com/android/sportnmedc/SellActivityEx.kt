package com.android.sportnmedc

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.sportnmedc.helpers.ToolbarHelper
import com.android.sportnmedc.helpers.Utils
import kotlinx.android.synthetic.main.activity_sell_card.*

class SellActivityEx:ExBaseActivity(R.layout.activity_sell_card),ToolbarHelper{


    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? ="Buakaw Banchamek"

    private var quantityTxt = 0

    override fun initMenuEvent(mToolbar: Toolbar?) {

    }
    companion object {
        fun newIntent(mActivity: AppCompatActivity): Intent {
            val intent = Intent(mActivity,SellActivityEx::class.java)
            return intent
        }
    }


    override fun onActivityCreated() {
        setupToolbar()
        minus_btn.setOnClickListener {
            if (quantityTxt > 0){
                quantityTxt--
            }else{
                quantityTxt = 0
            }

           qty_txt.text = quantityTxt.toString()

        }
        plus_btn.setOnClickListener {
            if (quantityTxt < 15){
                quantityTxt++
            }else{
                quantityTxt = 15
            }
            qty_txt.text = quantityTxt.toString()

        }
        confirm_btn.setOnClickListener {
           val priceStr =  price_edt.text.toString()
            val price = priceStr.toIntOrNull()?:0
            if (price > 0){
                startActivityForResult(SellConfirmActivityEx.newIntent(mActivity, price),Utils.REQUEST_CODE_BUYSELL)
            }else{
                Toast.makeText(mActivity,"The price must be more than 0.",Toast.LENGTH_SHORT).show()
            }


        }

    }




}