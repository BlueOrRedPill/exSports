package com.android.sportnmedc

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.sportnmedc.exdata.trades.TradeModel
import com.android.sportnmedc.exdata.user.ExUserSource
import com.android.sportnmedc.helpers.ToolbarHelper
import com.android.sportnmedc.helpers.Utils
import kotlinx.android.synthetic.main.activity_trade_new.*

class TradeNewActivityEx:ExBaseActivity(R.layout.activity_trade_new),ToolbarHelper{
    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? =""

    var mTradeModel = TradeModel(arrayListOf(0,0,0),arrayListOf(0,0,0),ExUserSource().getCurrentProfile())

    val cardGive:ArrayList<ImageView> = ArrayList()
    val cardGiveRemove:ArrayList<ImageView> = ArrayList()
    val cardNeed:ArrayList<ImageView> =ArrayList()
    val cardNeedRemove:ArrayList<ImageView> =ArrayList()

    override fun initMenuEvent(mToolbar: Toolbar?) {

    }

    companion object {
        fun newIntent(mActivity: AppCompatActivity): Intent {
            val intent = Intent(mActivity,TradeNewActivityEx::class.java)
//            intent.putExtra("tradeModel",tradeModel)
            return intent
        }
    }


    override fun onActivityCreated() {
        setupToolbar()
        ExUserSource().getCurrentProfile().let {
            profile_image.setImageResource(it.imgProfile)
            profile_name.text = it.name
        }
        cardGiveRemove.apply {
            add(0,card_give_remove_1)
            add(1,card_give_remove_2)
            add(2,card_give_remove_3)
        }
        cardNeedRemove.apply {
            add(0,card_need_remove_1)
            add(1,card_need_remove_2)
            add(2,card_need_remove_3)
        }
        cardGive.apply {
            add(0,card_give_1)
            add(1,card_give_2)
            add(2,card_give_3)
        }
        cardNeed.apply {
            add(0,card_need_1)
            add(1,card_need_2)
            add(2,card_need_3)
        }
        updateContent()

        confirm_btn.setOnClickListener {
            Toast.makeText(mActivity,"Success",Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    fun updateContent(){
        give_count.text = "(${mTradeModel.cardOut.filter { it != 0 }.size})"
        need_count.text = "(${mTradeModel.cardIn.filter { it != 0 }.size})"
         cardGive.forEachIndexed{ index, imageView ->
             if ( mTradeModel.cardOut[index] == 0){
                 cardGiveRemove[index].visibility = View.GONE
                 imageView.setImageResource(R.drawable.img_add_card)
                 imageView.setOnClickListener {
                     startActivityForResult(SelectCardActivityEx.newIntent(mActivity,mTradeModel,index,true),Utils.REQUEST_CODE_TRADE)
                 }
             }else{
                 cardGiveRemove[index].visibility = View.VISIBLE
                 cardGiveRemove[index].setOnClickListener {
                     mTradeModel.cardOut[index] = 0
                     updateContent()
                 }
                 imageView.setImageResource( mTradeModel.cardOut[index])
                 imageView.setOnClickListener {

                 }
             }
         }
        cardNeed.forEachIndexed { index, imageView ->
            if (mTradeModel.cardIn[index] == 0){
                cardNeedRemove[index].visibility= View.GONE
                imageView.setImageResource(R.drawable.img_add_card)
                imageView.setOnClickListener {
                    startActivityForResult(SelectCardActivityEx.newIntent(mActivity,mTradeModel,index,false),Utils.REQUEST_CODE_TRADE)
                }
            }else{
                cardNeedRemove[index].visibility= View.VISIBLE
                cardNeedRemove[index].setOnClickListener {
                    mTradeModel.cardIn[index] = 0
                    updateContent()
                }
                imageView.setImageResource( mTradeModel.cardIn[index])
                imageView.setOnClickListener {

                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Utils.REQUEST_CODE_TRADE && resultCode == Activity.RESULT_OK){
            data?.let {
                mTradeModel = it.getParcelableExtra("tradeModel")
                updateContent()
            }
        }else{
            super.onActivityResult(requestCode,resultCode,data)
        }

    }




}