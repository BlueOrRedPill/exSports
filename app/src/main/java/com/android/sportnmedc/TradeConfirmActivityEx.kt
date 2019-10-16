package com.android.sportnmedc

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.sportnmedc.exdata.trades.TradeModel
import com.android.sportnmedc.exdata.trades.TradeRepositories
import com.android.sportnmedc.exdata.user.ExUserSource
import com.android.sportnmedc.helpers.DialogHelper
import com.android.sportnmedc.helpers.ToolbarHelper
import kotlinx.android.synthetic.main.activity_trade_confirm.*

class TradeConfirmActivityEx:ExBaseActivity(R.layout.activity_trade_confirm),ToolbarHelper{


    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? = "TRADE CONFIRMATION"

    private lateinit var mTradeModel:TradeModel

    companion object {
        fun newIntent(mActivity: AppCompatActivity,mTradeModel: TradeModel): Intent {
            val intent = Intent(mActivity,TradeConfirmActivityEx::class.java)
            intent.putExtra("tradeModel",mTradeModel)
            return intent
        }
    }
    private var inFont = 0
    private var outFont = 0

    override fun initMenuEvent(mToolbar: Toolbar?) {

    }

    override fun onActivityCreated() {
        setupToolbar()
        mTradeModel = intent.getParcelableExtra("tradeModel")
        updateIn()
        updateOut()

        confirm_btn.setOnClickListener {
            DialogHelper.createConfirmDialog(mActivity,getString(R.string.app_name),"Do you want trade this card with other exUser?",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    TradeRepositories.addTradeData(mTradeModel)
                    setResult(Activity.RESULT_OK)
                    finish()
                }).show()
        }
    }

    private fun updateIn(){
        ExUserSource().getCurrentProfile().let {
            card_in_user_image.setImageResource(it.imgProfile)
            card_in_user_name.text = it.name
        }
        if (mTradeModel.cardIn.size > 1){
            card_in_back.visibility =  View.VISIBLE
            card_in_back.setImageResource(mTradeModel.cardIn[(inFont+1)%mTradeModel.cardIn.size])
        }else{
            card_in_back.visibility =  View.INVISIBLE
        }
        card_in.setImageResource(mTradeModel.cardIn[inFont])
        card_in.setOnClickListener {
            inFont =   (inFont+1) % mTradeModel.cardIn.size
            updateIn()
        }
        card_in_count.text = "${mTradeModel.cardIn.size} Cards"
    }
    private fun updateOut(){
        mTradeModel.exUser.let {
            card_out_user_image.setImageResource(it.imgProfile)
            card_out_user_name.text = it.name
        }
        if (mTradeModel.cardOut.size > 1){
            card_out_back.visibility =  View.VISIBLE
            card_out_back.setImageResource(mTradeModel.cardOut[(outFont+1)%mTradeModel.cardOut.size])
        }else{
            card_out_back.visibility =  View.INVISIBLE
        }
        card_out.setImageResource(mTradeModel.cardOut[outFont])
        card_out.setOnClickListener {
            outFont =   (outFont+1) % mTradeModel.cardOut.size
            updateOut()
        }
        card_out_count.text = "${mTradeModel.cardOut.size} Cards"
    }
}