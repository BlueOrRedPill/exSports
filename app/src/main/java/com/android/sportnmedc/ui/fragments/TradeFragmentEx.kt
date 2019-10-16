package com.android.sportnmedc.ui.fragments

import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.sportnmedc.ExBaseFragment
import com.android.sportnmedc.R
import com.android.sportnmedc.TradeNewActivityEx
import com.android.sportnmedc.adapters.TradeAdapter
import com.android.sportnmedc.exdata.BaseDataSource
import com.android.sportnmedc.exdata.trades.TradeRepositories
import com.android.sportnmedc.helpers.Utils
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_trade.*

class TradeFragmentEx: ExBaseFragment(R.layout.fragment_trade){

    var mAdapter:TradeAdapter?=null
    private var mRespositories = TradeRepositories()
    override fun startView(view: View) {

        mAdapter = TradeAdapter(mActivity)
        recyclerView.layoutManager = LinearLayoutManager(mActivity)
        recyclerView.adapter = mAdapter

//        mCardRespositories.load(object:BaseDataSource.OnLoad{
//            override fun onLoaded() {
//                search_auto_complete?.setAdapter( SearchCardAdapter(mActivity,R.layout.item_search,R.id.name_txt,mCardRespositories.getDataList() as ArrayList<CardModel>))
//            }
//        })

        search_auto_complete.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                mAdapter?.getFilter()?.filter(p0)

            }

        })
        tab_layout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                p0?.let {
                    updateData(it.position == 1)
                }
            }

        })
        updateData(false)
        new_btn.setOnClickListener {
            startActivityForResult(TradeNewActivityEx.newIntent(mActivity),Utils.REQUEST_CODE_TRADE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Utils.REQUEST_CODE_TRADE && resultCode == Activity.RESULT_OK){
            updateData(false)
        }

    }

    private fun updateData(isMyTrade:Boolean){
        search_container.visibility = if (isMyTrade) View.GONE else View.VISIBLE

        mRespositories.load(object: BaseDataSource.OnLoad{
            override fun onLoaded() {
                if (mAdapter?.itemCount?:0 > 0) mAdapter?.clear()
                mAdapter?.addDataAll(mRespositories.getDataList(),1)
                    if (isMyTrade){
                        mAdapter?.filter?.filter("Ekachai Sungsup")
                     }else{
                        mAdapter?.filter?.filter("")
                    }
            }
        })
    }


}