package com.android.sportnmedc.ui.fragments

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.sportnmedc.CardDetailActivityEx
import com.android.sportnmedc.R
import com.android.sportnmedc.adapters.BaseRecyclerAdapter
import com.android.sportnmedc.adapters.MarketAdapter
import com.android.sportnmedc.exdata.markets.MarketRepositories
import com.android.sportnmedc.helpers.Utils
import com.android.sportnmedc.network.model.MarketModel
import com.android.sportnmedc.ui.BaseFragment
import com.android.sportnmedc.viewmodels.MarketFragmentViewModel
import kotlinx.android.synthetic.main.fragment_market.*

class MarketFragmentEx: BaseFragment<MarketFragmentViewModel>(R.layout.fragment_market){

    override fun initViewModel(): MarketFragmentViewModel {
        return  ViewModelProviders.of(this).get(MarketFragmentViewModel::class.java).also {
            it.marketList().observe(this, mSuccess)
//            it.loadingIndicator().observe(this, mLoading)
            it.error().observe(this, mError)
        }
    }

    private var mMarketRepositories = MarketRepositories()
    var mMarketAdapter:MarketAdapter ?= null

    private var isDescending = false
    override fun startView(view: View) {
        mMarketAdapter =   MarketAdapter(mActivity,MarketAdapter.TYPE.BUY)
        recyclerView.adapter =mMarketAdapter
//        mMarketRepositories.load(object:BaseDataSource.OnLoad{
//            override fun onLoaded() {
//                updateContent(isDescending)
//            }
//
//        })
        mMarketAdapter?.setOnItemClick(object:BaseRecyclerAdapter.OnItemClicked{
            override fun onItemClicked(v: View, position: Int) {
                mMarketAdapter?.getItem(position)?.let {
                    startActivityForResult(CardDetailActivityEx.newIntent(mActivity, it),Utils.REQUEST_CODE_BUYSELL)
                }
            }

        })

//        sort_price.setOnClickListener {
//            updateContent(!isDescending)
//        }
    }

    override fun onResume() {
        super.onResume()
        mViewModel.bcValidServiceProducts()
    }

    private fun updateContent(toggle:Boolean, list:List<MarketModel>){
        isDescending = toggle
        sort_image.rotation = if (isDescending) 90f else 0f
        mMarketRepositories.sort(isDescending)
        if ( mMarketAdapter?.itemCount?:0 > 0){
            mMarketAdapter?.clear()
        }
        mMarketAdapter?.addDataAll(list,1)
    }

    private val mSuccess = Observer<List<MarketModel>> {
        updateContent(isDescending, it)
    }

    private val mError = Observer<String> {
        Toast.makeText(mActivity,it, Toast.LENGTH_SHORT).show()
    }
}