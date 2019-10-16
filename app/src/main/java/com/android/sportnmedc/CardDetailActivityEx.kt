package com.android.sportnmedc

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.sportnmedc.adapters.CardPageAdapter
import com.android.sportnmedc.helpers.FlipPageViewTransformer
import com.android.sportnmedc.helpers.ToolbarHelper
import com.android.sportnmedc.helpers.Utils
import com.android.sportnmedc.network.model.CardModel
import com.android.sportnmedc.network.model.MarketModel
import com.android.sportnmedc.ui.BaseActivity
import com.android.sportnmedc.viewmodels.CardDetailViewModel
import kotlinx.android.synthetic.main.activity_card_detail.*
import kotlinx.android.synthetic.main.toolbar.*

class CardDetailActivityEx:BaseActivity<CardDetailViewModel>(R.layout.activity_card_detail),ToolbarHelper{
    override fun initViewModel(): CardDetailViewModel {
        return  ViewModelProviders.of(this).get(CardDetailViewModel::class.java).also {
            it.card().observe(this, mSuccess)
            it.error().observe(this, mError)
        }
    }


    private var price:Int = 0
    private var cardModel:CardModel? = null
    private var marketModel:MarketModel? = null

    companion object {
//        fun newIntent(mActivity:AppCompatActivity,price:Int):Intent{
//            val intent = Intent(mActivity,CardDetailActivityEx::class.java)
//            intent.putExtra("price",price)
//            return intent
//        }

        fun newIntent(mActivity:AppCompatActivity, cardModel:CardModel):Intent{
            val intent = Intent(mActivity,CardDetailActivityEx::class.java)
            intent.putExtra("cardModel",cardModel.toString())
            return intent
        }

        fun newIntent(mActivity:AppCompatActivity, marketModel:MarketModel):Intent{
            val intent = Intent(mActivity,CardDetailActivityEx::class.java)
            intent.putExtra("marketModel",marketModel.toString())
            return intent
        }
    }

    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? ="Buakaw Banchamek"

    override fun initMenuEvent(mToolbar: Toolbar?) {

    }
    override fun onActivityCreated() {
        setupToolbar()
        val jsonString = intent.getStringExtra("cardModel")
        jsonString?.also {
            cardModel = CardModel.fromJson(it)
        }

        cardModel = intent.getStringExtra("cardModel")?.let {
            CardModel.fromJson(it)
        }

        marketModel = intent.getStringExtra("marketModel")?.let {
            MarketModel.fromJson(it)
        }

        if(marketModel != null) {
            mToolbar?.setToolbarTitle(marketModel!!.tokenName)
        } else {
            mToolbar?.setToolbarTitle(cardModel!!.dataJSON.athletes.name.nick)
        }

        price = intent.getIntExtra("price",0)
        viewpager.adapter = cardModel?.let { CardPageAdapter(supportFragmentManager, it) }
        viewpager.setPageTransformer(true,FlipPageViewTransformer())
        if (cardModel != null){
            toolbar_title.text = cardModel?.dataJSON?.athletes?.name?.nick ?: "Unknown"
            confirm_image.visibility = View.GONE
            confirm_txt.text = "SELL"
            confirm_btn.setOnClickListener {
                startActivityForResult(SellActivityEx.newIntent(mActivity), Utils.REQUEST_CODE_BUYSELL)
            }
        }else{
            mViewModel.cardDetail(marketModel?.tokenId)
            confirm_image.visibility = View.VISIBLE
            confirm_txt.text = "BUY"
            confirm_btn.setOnClickListener {
                startActivityForResult(BuyActivityEx.newIntent(mActivity, marketModel, cardModel), Utils.REQUEST_CODE_BUYSELL)
//                startActivityForResult(marketModel?.let { it1 -> BuyActivityEx.newIntent(mActivity, it1, cardModel) }, Utils.REQUEST_CODE_BUYSELL)
//                DialogHelper.createConfirmDialog(mActivity,getString(R.string.app_name),"Do you want to buy this card ?",
//                    DialogInterface.OnClickListener { _,_ ->
//                        startActivityForResult(ResultActivityEx.newIntent(mActivity,false),Utils.REQUEST_CODE_BUYSELL)
//                    }).show()
            }
        }
        tap_btn.setOnClickListener {
            if (viewpager.currentItem == 0){
                viewpager.setCurrentItem(1,true)
            }else{
                viewpager.setCurrentItem(0,true)
            }

        }
//        setUpProfile()
//        supportFragmentManager.beginTransaction().replace(R.id.user_detail_content,LibraryFragmentEx()).commitNowAllowingStateLoss()
    }

//    private fun setUpProfile(){
//       intent.getParcelableExtra<ExUserModel>("exUser").let {
//            name_txt.text = it.name
//            profile_image.setImageResource(it.imgProfile)
//            item_txt.text = it.items.toString()
//            complete_txt.text = it.cardQuality.toString()
//            score_txt.text = it.score.toString()
//        }
//
//    }

    private val mSuccess = Observer<CardModel> {
        cardModel = it
        viewpager.adapter = cardModel?.let { CardPageAdapter(supportFragmentManager, it) }
//        confirm_txt.text = "BUY ${it.totalQuantity} EXS"
    }

    private val mError = Observer<String> {
        Toast.makeText(mActivity,it, Toast.LENGTH_SHORT).show()
    }
}