package com.android.sportnmedc.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.sportnmedc.R
import com.android.sportnmedc.exdata.user.ExUserModel
import com.android.sportnmedc.userdetail.UserDetailActivityEx
import java.util.*


/**
 * Created by AndroidEB on 10/9/2017.
 */
class LeaderBoardAdapter(mActivity:AppCompatActivity):BaseRecyclerAdapter(mActivity){



    private var mTotalPage = 1
    private var mDataList:ArrayList<ExUserModel?> = ArrayList()

    private var currentExUser:ExUserModel?=null

    fun setCurrentUser(currentExUser:ExUserModel){
        this.currentExUser = currentExUser
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    fun getItem(postion:Int):ExUserModel?{
        return if (postion < mDataList.size) mDataList[postion] else null
    }

    fun clear(){
        mDataList.clear()
        notifyDataSetChanged()
    }
    fun addData(mData:ExUserModel?) {
        mDataList.add(mData)
        notifyItemInserted(mDataList.size - 1)
    }

    fun addDataAll(mDataList: List<ExUserModel>, mTotalPage: Int) {
        this.mTotalPage = mTotalPage
        val size = this.mDataList.size
        this.mDataList.addAll(mDataList)
        notifyItemRangeInserted(size, mDataList.size)

    }

    fun startLoading(mLayoutManager: RecyclerView.LayoutManager, currentPage: Int) {
        var currentPage = currentPage
        val layout = (mLayoutManager as? GridLayoutManager)?:mLayoutManager as LinearLayoutManager
        if (layout.itemCount > 0 && currentPage < mTotalPage) {
            val visibleThreshold = 2
            val totalItemCount = layout.itemCount
            val lastVisibleItem = layout.findLastVisibleItemPosition()
            if (!isLoading() && totalItemCount <= lastVisibleItem + visibleThreshold) {
                Log.e("page", "" + currentPage)
                setLoaded(true)
                if (mOnLoadMoreListener != null) {
                    addData(null)
                    currentPage++
                    mOnLoadMoreListener?.onLoadMore(currentPage)
                }
            }
        }
    }

    fun removeLoadingItem() {
        if (mDataList.size > 0) {
            if (mDataList.last() == null) {
                mDataList.removeAt(mDataList.lastIndex)
            }
        }
        setLoaded(false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_leaderboard, parent, false))
        } else super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ItemHolder -> {
                mDataList.get(position)?.let {
                    holder.updateContent(it,position)
                }

            }
            else -> super.onBindViewHolder(holder, position)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (mDataList[position]== null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }



    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var bgContent:LinearLayout = itemView.findViewById(R.id.bg_content)
        var profileImg:ImageView = itemView.findViewById(R.id.profile_image)
        var nameTxt:TextView = itemView.findViewById(R.id.name_txt)

        var rankTxt:TextView = itemView.findViewById(R.id.rank_txt)
        var itemTxt:TextView = itemView.findViewById(R.id.item_txt)
        var scoreTxt:TextView= itemView.findViewById(R.id.score_txt)

        init {
            itemView.setOnClickListener {
                mDataList.get(adapterPosition)?.let {
                    mActivity.startActivity(UserDetailActivityEx.newIntent(mActivity,it))
                }

            }
        }


        fun updateContent(mExUserModel:ExUserModel, position: Int){
            if (currentExUser?.name.equals(mExUserModel.name,true)){
                bgContent.setBackgroundResource(R.color.red_indicator)
            }else {
                if (position % 2 == 0) {
                    bgContent.setBackgroundResource(R.color.black_30)
                } else {
                    bgContent.setBackgroundResource(R.color.white_30)
                }
            }
            profileImg.setImageResource(mExUserModel.imgProfile)
            nameTxt.text = mExUserModel.name
            rankTxt.text =( position+1).toString()
            itemTxt.text = mExUserModel.items.toString()
            scoreTxt.text = mExUserModel.score.toString()
        }
    }



}