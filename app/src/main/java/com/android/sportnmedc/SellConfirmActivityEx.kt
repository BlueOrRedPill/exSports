package com.android.sportnmedc

import android.content.Intent
import android.view.View
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
import com.android.sportnmedc.viewmodels.SellConfirmActivityViewModel
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_sell_card_confirm.*

class SellConfirmActivityEx:BaseActivity<SellConfirmActivityViewModel>(R.layout.activity_sell_card_confirm),ToolbarHelper{
    override fun initViewModel(): SellConfirmActivityViewModel {
        return  ViewModelProviders.of(this).get(SellConfirmActivityViewModel::class.java).also {
            it.purchase().observe(this, mPurchaseSuccess)
            it.error().observe(this, mError)
            it.loadingIndicator().observe(this, mLoading)
        }
    }


    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? = "Confirmation"


    private var price:Int =0
    private var marketModel:MarketModel? = null
    private var cardModel:CardModel? = null
    private var quantity:Int = 0

    companion object {
        fun newIntent(mActivity: AppCompatActivity,price:Int): Intent {
            val intent = Intent(mActivity,SellConfirmActivityEx::class.java)
            intent.putExtra("price",price)
            return intent
        }

        fun newIntent(mActivity: AppCompatActivity, cardModel:CardModel): Intent {
            val intent = Intent(mActivity,SellConfirmActivityEx::class.java)
            intent.putExtra("cardModel", cardModel.toString())
            return intent
        }

        fun newIntent(mActivity: AppCompatActivity, marketModel: MarketModel, quantity: Int): Intent {
            val intent = Intent(mActivity,SellConfirmActivityEx::class.java)
            intent.putExtra("marketModel", marketModel.toString())
            intent.putExtra("quantity", quantity)
            return intent
        }
    }
    override fun initMenuEvent(mToolbar: Toolbar?) {

    }

    override fun onActivityCreated() {
        setupToolbar()

        cardModel = intent.getStringExtra("cardModel")?.let {
            CardModel.fromJson(it)
        }

        marketModel = intent.getStringExtra("marketModel")?.let {
                MarketModel.fromJson(it)
        }


        quantity = intent.getIntExtra("quantity",0)

        price = intent.getIntExtra("price",0)
        if (cardModel != null){
            sell_detail.visibility = View.VISIBLE
            info_message_txt.text = "do you really want to sell this card?"
            message_txt.text = "${marketModel!!.perPrice * quantity} EXS will be put on Marketplace."
            confirm_btn.setOnClickListener {
                startActivityForResult(ResultActivityEx.newIntent(mActivity,false),Utils.REQUEST_CODE_BUYSELL)
            }

        } else if (marketModel != null) {
            sell_detail.visibility = View.GONE
            info_message_txt.text = "do you really want to buy this card?"
            message_txt.text = "${marketModel!!.perPrice * quantity} EXS will be send to ${marketModel!!.ownerName}."
            confirm_btn.setOnClickListener {
                mViewModel.purchaseServiceProduct(marketModel!!.productId, quantity)
            }
            GlideApp.with(this).load("https://exsports.s3-ap-southeast-1.amazonaws.com/medias/images/carditems/" + marketModel!!.tokenId + ".png").into(img_card_buy)
        } else {
            sell_detail.visibility = View.GONE
            info_message_txt.text = "do you really want to buy this card?"
            message_txt.text = "${price} EXS will be send to User."
            confirm_btn.setOnClickListener {
                startActivityForResult(ResultActivityEx.newIntent(mActivity,true),Utils.REQUEST_CODE_BUYSELL)
            }
        }

    }

    private val mPurchaseSuccess = Observer<String> {
        startActivityForResult(ResultActivityEx.newIntent(mActivity,false, marketModel),Utils.REQUEST_CODE_BUYSELL)
    }

    private val mSellSuccess = Observer<JsonObject> {
        startActivityForResult(ResultActivityEx.newIntent(mActivity,true),Utils.REQUEST_CODE_BUYSELL)
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