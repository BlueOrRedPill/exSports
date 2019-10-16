package com.android.sportnmedc.repositories

import com.android.sportnmedc.BaseApplication
import com.android.sportnmedc.network.model.TradeModel
import com.android.sportnmedc.network.request.*

class TradeRepository :BaseRepository<TradeModel>(){

    fun tradeList(resultCount: Int, order: String, lastTradeId: Long) {
        networkClient.api.tradeList(BaseApplication.prefs.accessToken, ReqTradeList(resultCount, order, lastTradeId)).start
    }

    fun tradeSearch(search: String, searchType: String, resultCount: Int, order: String, lastTradeId: Long) {
        networkClient.api.tradeSearch(BaseApplication.prefs.accessToken, ReqTradeSearchList(search, searchType, resultCount, order, lastTradeId)).start
    }

    fun tradeRegister(giveCards: List<Long>, needCards: List<Long>) {
        networkClient.api.tradeRegister(BaseApplication.prefs.accessToken, ReqTradeRegister(giveCards, needCards)).start
    }

    fun tradeCancel(tradeId: Long) {
        networkClient.api.tradeCancel(BaseApplication.prefs.accessToken, ReqTradeCancel(tradeId)).start
    }

    fun tradeConfirm(tradeId: Long) {
        networkClient.api.tradeConfirm(BaseApplication.prefs.accessToken, ReqTradeConfirm(tradeId)).start
    }
}