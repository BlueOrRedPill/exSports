package com.android.sportnmedc.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.sportnmedc.LibraryDetailActivityEx
import com.android.sportnmedc.R
import com.android.sportnmedc.adapters.BaseRecyclerAdapter
import com.android.sportnmedc.adapters.LibraryAdapter
import com.android.sportnmedc.adapters.SportsTypeAdapter
import com.android.sportnmedc.exdata.BaseDataSource
import com.android.sportnmedc.exdata.libraries.LibraryRespositories
import com.android.sportnmedc.helpers.Utils
import com.android.sportnmedc.network.model.CollectionTypeModel
import com.android.sportnmedc.ui.BaseFragment
import com.android.sportnmedc.viewmodels.LibraryViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_library.*

class LibraryFragmentEx: BaseFragment<LibraryViewModel>(R.layout.fragment_library){
    override fun initViewModel(): LibraryViewModel {
        return  ViewModelProviders.of(this).get(LibraryViewModel::class.java).also {
            it.sportsList().observe(this, mSportsTypeOb)
            it.loadingIndicator().observe(this, mLoading)
            it.error().observe(this, mError)
        }
    }

    private var imageList:ArrayList<Int> ?= null
    private var mLibraryRespositories = LibraryRespositories()

    override fun startView(view: View) {
        imageList = LibraryRespositories.getOnSale()
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
                mViewModel.getSportsList()
                recyclerView.visibility = View.VISIBLE
                onsale_content.visibility = View.GONE
                val mAdapter =  SportsTypeAdapter(mActivity)
                recyclerView.layoutManager = LinearLayoutManager(mActivity)
                recyclerView.adapter = mAdapter
//                mLibraryRespositories.load(object: BaseDataSource.OnLoad{
//                    override fun onLoaded() {
//                        mAdapter.addDataAll(mLibraryRespositories.getDataList(),1)
//                    }
//                })
                mAdapter.setOnItemClick(object:BaseRecyclerAdapter.OnItemClicked{
                    override fun onItemClicked(v: View, position: Int) {
                        mAdapter.getItem(position)?.let {
                            startActivityForResult(LibraryDetailActivityEx.newIntent(mActivity,it),Utils.REQUEST_CODE_BUYSELL)
                        }
                    }

                })
            }
            1->   {
                recyclerView.visibility = View.GONE
                onsale_content.visibility = View.VISIBLE
                addCardDemo()
//                val mCardAdapter =   MarketAdapter(mActivity,MarketAdapter.TYPE.ON_SALE)
//                recyclerView.layoutManager = GridLayoutManager(mActivity,3)
//                recyclerView.adapter = mCardAdapter
//
//                mCardAdapter.addDataAll(DataHelper().get3Card(),1)
//                mCardAdapter.setOnItemClick(object :BaseRecyclerAdapter.OnItemClicked{
//                    override fun onItemClicked(v: View, position: Int) {
//                        tab_home.getTabAt(0)?.select()
//                    }
//
//                })
            }
        }

    }
    private fun addCardDemo(){
        if (card_content.childCount > 0) card_content.removeAllViews()
        for (i in 0..2){
            val view = LayoutInflater.from(mActivity).inflate(R.layout.item_card_blog,card_content,false)
            val image1: ImageView = view.findViewById(R.id.image_1)
            val image2: ImageView = view.findViewById(R.id.image_2)
            val image3: ImageView = view.findViewById(R.id.image_3)
            imageList?.let {
                image1.setImageResource(it[i*3 + 0])
                image2.setImageResource(it[i*3 + 1])
                image3.setImageResource(it[i*3 + 2])
            }

            card_content.addView(view)
        }
    }

//    private val mSuccess = Observer<UserModel> {
//        Toast.makeText(mActivity,"Hello : ${it.name}", Toast.LENGTH_SHORT).show()
//        startActivity(HomeActivityEx.newIntent(mActivity))
//        finish()
//    }

    private val mSportsTypeOb = Observer<List<CollectionTypeModel>> {
        (recyclerView.adapter as SportsTypeAdapter).addDataAll(it, 1)

//        (recyclerView.adapter as SportsTypeAdapter).addDataAll(it)
    }

    private val mError = Observer<String> {
        Toast.makeText(mActivity,it, Toast.LENGTH_SHORT).show()
    }

    private val mLoading = Observer<Boolean> {
//        if (it){
//            confirm_btn.isEnabled = false
//            content_loading.show()
//        }else{
//            confirm_btn.isEnabled = true
//            content_loading.hide()
//        }
    }
}