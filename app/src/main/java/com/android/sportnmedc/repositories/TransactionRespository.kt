package com.android.sportnmedc.repositories

import com.android.sportnmedc.BaseApplication
import com.android.sportnmedc.network.model.TransactionModel
import com.android.sportnmedc.network.request.*

class TransactionRespository :BaseRepository<TransactionModel>(){

    fun walletTransactionList(targetUid: Long, resultCount: Int, order: String, lastTransactionId: Long) {
        networkClient.api.walletTransactionList(BaseApplication.prefs.accessToken, ReqWalletTransactionList(targetUid, resultCount, order, lastTransactionId)).start
    }

    fun walletTransactionDetail(lastTransactionId: Long) {
       networkClient.api.walletTransactionDetail(BaseApplication.prefs.accessToken, ReqWalletTransactionDetail(lastTransactionId)).start
    }

    fun walletBuyCoin(buyExs: Int, code: String) {
        networkClient.api.walletBuyCoin(BaseApplication.prefs.accessToken, ReqWalletBuyCoin(buyExs, code)).start
    }

    fun walletSendCoin(targetUid: Long, sendExs: Int) {
        networkClient.api.walletSendCoin(BaseApplication.prefs.accessToken, ReqWalletSendCoin(targetUid, sendExs)).start
    }

    fun libraryItemTransactionList(cardId: Long, resultCount: Int, order: String, lastTransactionId: Long) {
       networkClient.api.libraryItemTransactionList(BaseApplication.prefs.accessToken, ReqLibraryItemTransactionList(cardId, resultCount, order, lastTransactionId)).start
    }

    fun marketBuy(marketId: Long, count: Int, price: Int) {
        networkClient.api.marketBuy(BaseApplication.prefs.accessToken, ReqMarketBuy(marketId, count, price)).start
    }

    fun marketCancel(marketId: Long) {
         networkClient.api.marketCancel(BaseApplication.prefs.accessToken, ReqMarketCancel(marketId)).start
    }

}