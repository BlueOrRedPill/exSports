package com.android.sportnmedc

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.sportnmedc.helpers.GlideApp
import com.android.sportnmedc.helpers.ToolbarHelper
import com.android.sportnmedc.network.model.MarketModel
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.activity_result.confirm_btn
import kotlinx.android.synthetic.main.activity_result.img_card_buy
import kotlinx.android.synthetic.main.activity_result.message_txt
import kotlinx.android.synthetic.main.activity_sell_card_confirm.*

class ResultActivityEx:ExBaseActivity(R.layout.activity_result),ToolbarHelper{


    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? = "Buakaw Banchamek"


    private var isSell:Boolean =true
    private var marketModel:MarketModel? = null

    companion object {
        fun newIntent(mActivity: AppCompatActivity,isSell:Boolean): Intent {
            val intent = Intent(mActivity,ResultActivityEx::class.java)
            intent.putExtra("isSell",isSell)
            return intent
        }

        fun newIntent(
            mActivity: AppCompatActivity,
            isSell:Boolean, marketModel: MarketModel?
        ): Intent {
            val intent = Intent(mActivity,ResultActivityEx::class.java)
            intent.putExtra("isSell",isSell)
            intent.putExtra("marketModel", marketModel.toString())
            return intent
        }
    }

    override fun initMenuEvent(mToolbar: Toolbar?) {

    }
    override fun onActivityCreated() {
        setupToolbar()
        marketModel = intent.getStringExtra("marketModel")?.let {
            MarketModel.fromJson(it)
        }
        isSell = intent.getBooleanExtra("isSell",true)
        setResult(Activity.RESULT_OK)
        if (isSell){
            image_content.visibility = View.GONE
            success_image.visibility = View.VISIBLE
            message_txt.text = "Your card has been added to the market."
        }else{
            image_content.visibility = View.VISIBLE
            success_image.visibility = View.GONE
            message_txt.text = "This card has been added to your collection."
            GlideApp.with(this).load("https://exsports.s3-ap-southeast-1.amazonaws.com/medias/images/carditems/" + marketModel!!.tokenId + ".png").into(img_card_buy)
            mToolbar?.setToolbarTitle(marketModel!!.tokenName)
        }

        confirm_btn.setOnClickListener {
            finish()
        }

    }




}