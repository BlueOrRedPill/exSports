package com.android.sportnmedc

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.sportnmedc.helpers.GlideApp
import com.android.sportnmedc.helpers.ToolbarHelper
import com.android.sportnmedc.helpers.Utils
import com.android.sportnmedc.network.model.CardModel
import com.android.sportnmedc.network.model.MarketModel
import com.android.sportnmedc.ui.BaseActivity
import com.android.sportnmedc.viewmodels.BuyActivityViewModel
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_buy_card.*
import kotlinx.android.synthetic.main.activity_sell_card.confirm_btn
import kotlinx.android.synthetic.main.activity_sell_card.minus_btn
import kotlinx.android.synthetic.main.activity_sell_card.plus_btn
import kotlinx.android.synthetic.main.activity_sell_card.qty_txt

class BuyActivityEx:BaseActivity<BuyActivityViewModel>(R.layout.activity_buy_card),ToolbarHelper{
    override fun initViewModel(): BuyActivityViewModel {
        return  ViewModelProviders.of(this).get(BuyActivityViewModel::class.java).also {
            it.purchase().observe(this, mSuccess)
            it.error().observe(this, mError)
        }
    }

    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? ="Buakaw Banchamek"

    private var quantityTxt = 0
    private var marketModel:MarketModel? = null
    private var cardModel:CardModel? = null

    companion object {
        fun newIntent(mActivity: AppCompatActivity, cardModel: CardModel): Intent {
            val intent = Intent(mActivity, BuyActivityEx::class.java)
            intent.putExtra("cardModel", cardModel.toString())
            return intent
        }

        fun newIntent(mActivity: AppCompatActivity, marketModel: MarketModel?, cardModel: CardModel?): Intent {
            val intent = Intent(mActivity, BuyActivityEx::class.java)
            intent.putExtra("marketModel", marketModel.toString())
            intent.putExtra("cardModel", cardModel.toString())
            return intent
        }
    }

    override fun initMenuEvent(mToolbar: Toolbar?) {

    }
    override fun onActivityCreated() {
        setupToolbar()

//        marketModel = MarketModel.fromJson(intent.getStringExtra("marketModel"))
//        cardModel = CardModel.fromJson(intent.getStringExtra("cardModel"))
        marketModel = intent.getStringExtra("marketModel").let {
            MarketModel.fromJson(it)
        }

        cardModel = intent.getStringExtra("cardModel")?.let {
            CardModel.fromJson(it)
        }

        confirm_btn.setOnClickListener {
            startActivityForResult(SellConfirmActivityEx.newIntent(mActivity,
                marketModel!!, quantityTxt), Utils.REQUEST_CODE_BUYSELL)
        }

        minus_btn.setOnClickListener {
            if (quantityTxt > 0){
                quantityTxt--
            }else{
                quantityTxt = 0
            }

            qty_txt.text = quantityTxt.toString()
            total_text.text = "${marketModel!!.perPrice * quantityTxt} EXS"
        }
        plus_btn.setOnClickListener {
            if (quantityTxt < marketModel!!.quantity){
                quantityTxt++
            }else{
                quantityTxt = marketModel!!.quantity
            }
            qty_txt.text = quantityTxt.toString()
            total_text.text = "${marketModel!!.perPrice * quantityTxt} EXS"
        }
//        cardPrice.text = "${mData.price} EXS"
        quantity_text.text = "AVAILABLE : ${marketModel!!.quantity} CARDS"
        name_text.text = marketModel!!.tokenName
        price_txt.text = "${marketModel!!.perPrice} EXS"
        total_text.text = "${marketModel!!.perPrice * quantityTxt} EXS"
        GlideApp.with(this).load("https://exsports.s3-ap-southeast-1.amazonaws.com/medias/images/carditems/" + marketModel!!.tokenId + ".png").into(img_card_buy)
        usd_text.text = " = \$${marketModel!!.perPrice} USD"

        mToolbar?.setToolbarTitle(marketModel!!.tokenName)
    }


    private val mSuccess = Observer<JsonObject> {
    }

    private val mError = Observer<String> {
        Toast.makeText(mActivity,it, Toast.LENGTH_SHORT).show()
    }

}