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
import kotlinx.android.synthetic.main.activity_trade_detail.*

class TradeDetailActivityEx:ExBaseActivity(R.layout.activity_trade_detail),ToolbarHelper{
    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? =""

    val cardGive:ArrayList<ImageView> = ArrayList()
    val cardNeed:ArrayList<ImageView> =ArrayList()

    override fun initMenuEvent(mToolbar: Toolbar?) {

    }

    companion object {
        fun newIntent(mActivity: AppCompatActivity,tradeModel:TradeModel): Intent {
            val intent = Intent(mActivity,TradeDetailActivityEx::class.java)
            intent.putExtra("tradeModel",tradeModel)
            return intent
        }
    }


    override fun onActivityCreated() {
        setupToolbar()
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
        intent.getParcelableExtra<TradeModel>("tradeModel")?.let {
            updateContent(it)
        }
    }

    fun updateContent(tradeModel:TradeModel){
        cardNeed.forEachIndexed { index, imageView ->
            if (tradeModel.cardIn.size > index){
                imageView.visibility = View.VISIBLE
                imageView.setImageResource(tradeModel.cardIn[index])
            }else{
                imageView.visibility = View.GONE
            }
        }
        cardGive.forEachIndexed { index, imageView ->
            if (tradeModel.cardOut.size > index){
                imageView.visibility = View.VISIBLE
                imageView.setImageResource(tradeModel.cardOut[index])
            }else{
                imageView.visibility = View.GONE
            }
        }
        give_count.text = "(${tradeModel.cardOut.size})"
        need_count.text = "(${tradeModel.cardIn.size})"
        tradeModel.exUser.let {
            profile_image.setImageResource(it.imgProfile)
            profile_name.text = it.name
            if ( ExUserSource().getCurrentProfile().name.equals(it.name)){
                confirm_container.visibility = View.INVISIBLE
            }else{
                confirm_container.visibility = View.VISIBLE
                confirm_btn.setOnClickListener {
                    setResult(Activity.RESULT_OK)
                    Toast.makeText(mActivity,"Success.",Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

        }


    }




}