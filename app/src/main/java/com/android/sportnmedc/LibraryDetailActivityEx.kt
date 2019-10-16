package com.android.sportnmedc

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.sportnmedc.helpers.GlideApp
import com.android.sportnmedc.helpers.ToolbarHelper
import com.android.sportnmedc.helpers.Utils
import com.android.sportnmedc.network.model.CardModel
import com.android.sportnmedc.network.model.CollectionTypeModel
import com.android.sportnmedc.ui.BaseActivity
import com.android.sportnmedc.viewmodels.LibraryDetailViewModel
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_library_detail.*
import kotlin.math.ceil


class LibraryDetailActivityEx:BaseActivity<LibraryDetailViewModel>(R.layout.activity_library_detail),ToolbarHelper{
    override fun initViewModel(): LibraryDetailViewModel {
        return  ViewModelProviders.of(this).get(LibraryDetailViewModel::class.java).also {
            it.cardList().observe(this, mSuccess)
            it.tokensOwned().observe(this, mTokensSuccess)
            it.loadingIndicator().observe(this, mLoading)
            it.error().observe(this, mError)
        }
    }

    private var imageList:ArrayList<CardModel> = ArrayList()
    private var tokensList:ArrayList<CardModel> = ArrayList()

    companion object {
        fun newIntent(mActivity: AppCompatActivity, mData:CollectionTypeModel): Intent {
            val intent = Intent(mActivity,LibraryDetailActivityEx::class.java)
            intent.putExtra("library",mData)
            return intent
        }
    }

    override fun initMenuEvent(mToolbar: Toolbar?) {

    }

    override fun hasBackMenu(): Boolean =true

    override fun onActivityCreated() {
        setupToolbar()

//        card_content.setOnClickListener {
//            startActivityForResult(CardDetailActivityEx.newIntent(mActivity,0),Utils.REQUEST_CODE_BUYSELL)
//        }

        intent.getParcelableExtra<CollectionTypeModel>("library").let {
            mToolbar?.setToolbarTitle(it.sportsName)
            if(it.sportsId == "jiujitsu") {
                cover_img.setImageResource(R.drawable.ex_library_udo)
            } else {
                cover_img.setImageResource(R.drawable.ex_library_muaythai)
            }
            name_txt.text = it.sportsName
//            sportsId: String, count: Int, sortOrder: String, lastId: Long

            mViewModel.getCardList(it.sportsId, 20, 1, 0)
//            percent_all_txt.text = "${it.currentCount}/${it.maxCount} complete"
//            imageList = LibraryRespositories.getLibraryItems(it.sportsName)
//            addCardDemo()
        }
        mViewModel.getTokensOwned()
    }

    private fun addCardDemo() {
        if (card_content.childCount > 0) card_content.removeAllViews()
        val size = imageList?.size?:0
//        val level =  if (size > 6) 2 else if (size > 3) 1 else 0
        val level = ceil(size.toFloat() / 3.toFloat()).toInt() - 1
        for (i in 0..level){
            val view = LayoutInflater.from(mActivity).inflate(R.layout.item_card_blog,card_content,false)
            val container:ArrayList<RelativeLayout> = arrayListOf(view.findViewById(R.id.container_1), view.findViewById(R.id.container_2), view.findViewById(R.id.container_3))
            val image:ArrayList<ImageView> = arrayListOf(view.findViewById(R.id.image_1), view.findViewById(R.id.image_2), view.findViewById(R.id.image_3))
            val count:ArrayList<TextView> = arrayListOf(view.findViewById(R.id.count_1), view.findViewById(R.id.count_2), view.findViewById(R.id.count_3))
            for (j in 0..2){
                val index = i*3+j
                updateItemView(container[j],image[j],count[j],index)
            }
//            val index1 = i*3 + 0
//            val index2 = i*3 + 1
//            val index3 = i*3 + 2
//            val count1:ImageView = view.findViewById(R.id.count_1)
//            val count2:ImageView = view.findViewById(R.id.count_1)
//            val count3:ImageView = view.findViewById(R.id.count_1)
//            updateItemView(image1,index1)
//            updateItemView(image2,index2)
//            updateItemView(image3,index3)
            card_content.addView(view)
        }
    }

    private fun updateItemView(container:RelativeLayout,imageView:ImageView,countView:TextView,index:Int){
        container.visibility = View.INVISIBLE
        imageList?.let {
            if (index < it.size){
                container.visibility = View.VISIBLE
                GlideApp.with(this).load(it[index].dataJSON.card.image.front).into(imageView)
                    for (i in 0 until tokensList.size) {
                        if (it[index].dataJSON.token.id == tokensList[i].tokenId) {
                            Logger.d("예이이이이")
                            container.alpha = 1f
                            countView.visibility = View.VISIBLE
                            countView.text = "X " + tokensList[i].balance.toString()

                            imageView.setOnClickListener {
                                startActivityForResult(CardDetailActivityEx.newIntent(mActivity, imageList[index]),Utils.REQUEST_CODE_BUYSELL)
                            }
                            break
                        }
                    }
//                GlideApp.with(this).load("http://exsports.s3-ap-southeast-1.amazonaws.com/medias/images/carditems/1000019.png").into(imageView)
//                https://exsports.s3-ap-southeast-1.amazonaws.com/medias/images/carditems/1000019.png
//                GlideApp.with(this).loa
//                Glide.with(this).load(it[index].dataJSON.card.image.front).into(imageView)
//                GlideManager.with(this).load(it[index].dataJSON.card.image.front).into(imageView)
//                fun ImageView.loadUrl(imageUrl: String?) {
//                    Glide.with(this).load(imageUrl).into(this)
//                }

//                it[index].cardImage
//                imageView.setImageResource(it[index])
//                countView.text = "X ${Random.nextInt(5)+1}"
            }
        }
    }

    private val mSuccess = Observer<List<CardModel>> {
        imageList.clear()
        imageList.addAll(it)
        addCardDemo()
//        imageList.addAll(it)
//        (recyclerView.adapter as SportsTypeAdapter).addDataAll(it, 1)
    }

    private val mTokensSuccess = Observer<List<CardModel>> {
        tokensList.clear()
        tokensList.addAll(it)
        addCardDemo()
//        imageList.addAll(it)
//        (recyclerView.adapter as SportsTypeAdapter).addDataAll(it, 1)
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