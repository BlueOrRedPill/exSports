package com.android.sportnmedc.userdetail

import com.android.sportnmedc.exdata.trades.TradeModel
import com.android.sportnmedc.exdata.user.ExUserModel

class UserDetailPresenter(var mExUser: ExUserModel, var mView:UserDetailContract.View):UserDetailContract.Presenter{


    init {
        mView.setPresenter(this)
    }


    override fun start() {
        mView.showProfile(mExUser)
    }

    override fun openSelectItem() {
        mView.showSelectItem(true, TradeModel(ArrayList(),ArrayList(),mExUser))

    }

}