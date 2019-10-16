package com.android.sportnmedc.ui.fragments

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.sportnmedc.ExBaseFragment
import com.android.sportnmedc.R
import com.android.sportnmedc.adapters.TransactionAdapter
import com.android.sportnmedc.helpers.GlideApp
import com.android.sportnmedc.network.model.CardModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.item_back_card.info_content
import kotlinx.android.synthetic.main.item_back_card.tab_home
import kotlinx.android.synthetic.main.item_back_card.transaction_content
import kotlinx.android.synthetic.main.item_back_card_temp.*
import kotlinx.android.synthetic.main.item_card_info.view.*
import kotlinx.android.synthetic.main.item_card_transaction.*

class BackCardFragmentEx(val cardModel: CardModel): ExBaseFragment(R.layout.item_back_card_temp){


    override fun startView(view: View) {
        GlideApp.with(view).load(cardModel.dataJSON.card.image.back).into(bg_background_card)

        tab_home.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                p0?.let {
                    updateData(it.position)
                }
            }

        })

        updateData(0)
    }

    fun updateData(index:Int){
        when(index){
            0->   {
                transaction_content.visibility = View.GONE
                info_content.visibility = View.VISIBLE
                info_content.card_info_name.text = cardModel.dataJSON.athletes.name.nick
                info_content.card_info_id.text = "Card ID : " + cardModel.dataJSON.card.id.toString()
                info_content.card_info_sport.text = "Sports : " + cardModel.dataJSON.sports.name
                info_content.card_info_event.text = "Event : " + cardModel.dataJSON.edition.name
                info_content.card_info_country.text = "Country : " + cardModel.dataJSON.athletes.nationality.short
            }
            1->   {
                transaction_content.visibility = View.VISIBLE
                info_content.visibility = View.GONE
                updateTransaction()
            }
        }

    }

    private fun updateTransaction(){
        val mAdapter =  TransactionAdapter(mActivity)
        recyclerView.layoutManager = LinearLayoutManager(mActivity)
        recyclerView.adapter = mAdapter
    }
}