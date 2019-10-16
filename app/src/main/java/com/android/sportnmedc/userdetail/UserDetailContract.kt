package com.android.sportnmedc.userdetail

import com.android.sportnmedc.BasePresenter
import com.android.sportnmedc.BaseView
import com.android.sportnmedc.exdata.trades.TradeModel
import com.android.sportnmedc.exdata.user.ExUserModel

interface UserDetailContract{
    interface View:BaseView<Presenter>{
        fun showProfile(mProfile:ExUserModel)

        fun showSelectItem(itemIn:Boolean,mTradeModel:TradeModel)

    }

    interface Presenter:BasePresenter{
        fun openSelectItem()

    }
}