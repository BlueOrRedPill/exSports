package com.android.sportnmedc.ui.fragments

import android.view.View
import com.android.sportnmedc.ExBaseFragment
import com.android.sportnmedc.R
import com.android.sportnmedc.exdata.user.ExUserSource
import com.android.sportnmedc.helpers.GlideApp
import com.android.sportnmedc.network.model.CardModel
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.item_font_card_temp.*

class FontCardFragmentEx(val cardModel: CardModel): ExBaseFragment(R.layout.item_font_card_temp){

    private var mUserRepositories = ExUserSource()

    override fun startView(view: View) {
        GlideApp.with(view).load(cardModel?.dataJSON?.card?.image?.front).into(front_card)
    }

}