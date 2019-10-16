package com.android.sportnmedc

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.sportnmedc.adapters.BaseRecyclerAdapter
import com.android.sportnmedc.adapters.UserTradeAdapter
import com.android.sportnmedc.exdata.BaseDataSource
import com.android.sportnmedc.exdata.cards.CardModel
import com.android.sportnmedc.exdata.trades.TradeModel
import com.android.sportnmedc.exdata.user.ExUserSource
import com.android.sportnmedc.helpers.ToolbarHelper
import com.android.sportnmedc.helpers.Utils
import kotlinx.android.synthetic.main.activity_trade.*

class TradeActivityEx:ExBaseActivity(R.layout.activity_trade),ToolbarHelper{

    val mUserRepositories = ExUserSource()

    override fun hasBackMenu(): Boolean =true
    override fun toolbarTitle(): String? ="Trade"

    override fun initMenuEvent(mToolbar: Toolbar?) {

    }
    companion object {
        fun newIntent(mActivity: AppCompatActivity,card:CardModel): Intent {
            val intent = Intent(mActivity,TradeActivityEx::class.java)
            intent.putExtra("card",card)
            return intent
        }
    }


    override fun onActivityCreated() {
        setupToolbar()
        intent.getParcelableExtra<CardModel>("card")?.also {
            profile_img.setImageResource(it.image)
            name_txt.text = it.name
            id_txt.text = "Card ID : ${it.cardId}"
            skill_txt.text = "Skill : ${it.skill}"
            sport_txt.text = "Sport : ${it.sport}"
            event_txt.text = "Event : ${it.event}"
            country_txt.text = "Country : ${it.country}"
        }
      val mAdapter =   UserTradeAdapter(mActivity)
        recyclerView.adapter =mAdapter
            mUserRepositories.load(object: BaseDataSource.OnLoad{
                override fun onLoaded() {
                    mAdapter.addDataAll(mUserRepositories.getDataList(),1)
                }
            })
        mAdapter.setOnItemClick(object:BaseRecyclerAdapter.OnItemClicked{
            override fun onItemClicked(v: View, position: Int) {
                mAdapter.getItem(position)?.also {
                    startActivityForResult(
                        SelectItemActivityEx.newIntent(
                            mActivity,
                            TradeModel(ArrayList(),ArrayList(),it)
                        ), Utils.REQUEST_CODE_TRADE)
                }

            }

        })
    }




}