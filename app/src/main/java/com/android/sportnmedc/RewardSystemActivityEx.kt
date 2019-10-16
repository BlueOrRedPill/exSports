package com.android.sportnmedc

import android.content.Intent
import android.graphics.Color
import android.text.Html
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.sportnmedc.helpers.ToolbarHelper
import kotlinx.android.synthetic.main.activity_reward_system.*

class RewardSystemActivityEx:ExBaseActivity(R.layout.activity_reward_system),ToolbarHelper{


    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? =""

    private var name = arrayListOf("Buakaw Win",
        "Buakaw Win",
        "Buakaw Lose",
        "Buakaw Win",
        "Buakaw Win",
        "Buakaw Win",
        "Buakaw Lose",
        "Buakaw Win",
        "Buakaw Lose",
        "Buakaw Win")
    private var point = arrayListOf("+50","+35","-20","+25","+20","+15","-10","+10","-5","+5")

    companion object {
        fun newIntent(mActivity: AppCompatActivity): Intent {
            val intent = Intent(mActivity,RewardSystemActivityEx::class.java)
            return intent
        }
    }
    override fun initMenuEvent(mToolbar: Toolbar?) {

    }

    override fun onActivityCreated() {
        setupToolbar()
        updateContent()
        add_coin.setOnClickListener {
            startActivity(PaymentActivityEx.newIntent(mActivity))
        }
    }

    private fun updateContent(){
        for(i in 0..name.size-1){
            val view = LayoutInflater.from(mActivity).inflate(R.layout.item_reward_system,reward_content,false)
            val nameTxt:TextView = view.findViewById(R.id.name_txt)
            val pointTxt:TextView = view.findViewById(R.id.point_txt)

            nameTxt.text = Html.fromHtml("<b>${name[i]}</b> at All Star Fight")
            pointTxt.text = point[i]
            if (i == 2 || i == 6 || i==8 ){
                pointTxt.setTextColor(Color.parseColor("#ff0000"))
            }else{
                pointTxt.setTextColor(Color.parseColor("#50cc25"))
            }
            reward_content.addView(view)
        }
    }




}