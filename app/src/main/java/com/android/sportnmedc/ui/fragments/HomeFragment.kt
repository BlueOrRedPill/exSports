package com.android.sportnmedc.ui.fragments

import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.sportnmedc.BaseApplication
import com.android.sportnmedc.R
import com.android.sportnmedc.helpers.GlideApp
import com.android.sportnmedc.network.model.MarketModel
import com.android.sportnmedc.network.model.UserModel
import com.android.sportnmedc.ui.BaseFragment
import com.android.sportnmedc.viewmodels.HomeFragmentViewModel
import com.google.gson.JsonObject
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_fighter_card.view.*
import kotlinx.android.synthetic.main.layout_header_profile.*

class HomeFragment: BaseFragment<HomeFragmentViewModel>(R.layout.fragment_home){

    var profile = UserModel.fromJson(BaseApplication.prefs.profile)
//    var profile:BaseApplication.p

    override fun initViewModel(): HomeFragmentViewModel {
        return  ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java).also {
            it.serviceProducts().observe(this, mSuccess)
//            it.user().observe(this, mSuccess)
//            it.loadingIndicator().observe(this, mLoading)
//            it.error().observe(this, mError)
        }
    }
//
    override fun startView(view: View) {
//        setUpLeaderBoard()
        mViewModel.bcValidServiceProducts()
        setUpProfile()
//        leaderboard_btn.setOnClickListener {
//            startActivity(LeaderBoardActivityEx.newIntent(mActivity))
//        }
    }
//
//    private fun setUpLeaderBoard(){
//        val mLeaderBoardAdapter =  LeaderBoardAdapter(mActivity)
//        recyclerView.layoutManager = LinearLayoutManager(mActivity)
//        recyclerView.adapter = mLeaderBoardAdapter
//        mUserRepositories.load(object: BaseDataSource.OnLoad{
//            override fun onLoaded() {
//               val index =  mUserRepositories.getDataList().indexOfFirst {  userModel -> userModel.name.equals(mUserRepositories.getCurrentProfile().name,true) }
//                mLeaderBoardAdapter.setCurrentUser( mUserRepositories.getCurrentProfile())
//                mLeaderBoardAdapter.addData(mUserRepositories.getDataIndex(index-1))
//                mLeaderBoardAdapter.addData(mUserRepositories.getDataIndex(index))
//                mLeaderBoardAdapter.addData(mUserRepositories.getDataIndex(index+1))
//            }
//        })
//        mLeaderBoardAdapter.setOnItemClick(object : BaseRecyclerAdapter.OnItemClicked{
//            override fun onItemClicked(v: View, position: Int) {
//            }
//
//        })
//    }
//
    private fun setUpProfile(){
    profile.let {
        Logger.json(it.toString())
        name_txt.text = it.name
        GlideApp.with(this).load(it.image).placeholder(R.drawable.ic_menu_profile).into(profile_image)
        complete_txt.text = 0.toString()
        score_txt.text = it.itemScore.toString()
        item_txt.text = it.itemCount.toString()

//        "itemCount": 0,
//        D/PRETTY_LOGGER: │       "itemScore": 0,
//        D/PRETTY_LOGGER: │       "balanceCoin": 0,
    }
//        mUserRepositories.getCurrentProfile().let {
//            name_txt.text = it.name
//            profile_image.setImageResource(it.imgProfile)
//            item_txt.text = it.items.toString()
//            complete_txt.text = it.cardQuality.toString()
//            score_txt.text = it.score.toString()
//        }
    }
//
    private fun addNewContent(){
//        val image = arrayListOf(R.drawable.b01,R.drawable.ej01,R.drawable.m01,R.drawable.v01,R.drawable.j01)
        if (new_content.childCount > 0 ) new_content.removeAllViews()
        for (i in 0 until cardList.size){
            val view = LayoutInflater.from(mActivity).inflate(R.layout.item_home_fighter_card,new_content,false)
            GlideApp.with(this@HomeFragment).load("https://exsports.s3-ap-southeast-1.amazonaws.com/medias/images/carditems/" + cardList[i].tokenId + ".png").into(view.img_card_buy)
            view.user_icon.visibility = View.GONE
            view.card_price.text =  "${cardList[i].perPrice} EXS"

            view.user_name.text = cardList[i].ownerName
            view.count_card.visibility = View.VISIBLE
            view.count_card.text = cardList[i].quantity.toString()
            new_content.addView(view)
        }
    }

    private var cardList:ArrayList<MarketModel> = ArrayList()
    private val mSuccess = Observer<List<MarketModel>> {
        cardList.clear()
        cardList.addAll(it)
        addNewContent()
    }

    private val mSuccessBalance = Observer<JsonObject> {

    }
}