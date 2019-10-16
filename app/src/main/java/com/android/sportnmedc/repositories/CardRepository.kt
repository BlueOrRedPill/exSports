package com.android.sportnmedc.repositories

import com.android.sportnmedc.BaseApplication
import com.android.sportnmedc.network.request.*

class CardRepository<T> :BaseRepository<T>(){

    fun libraryItemList(resultCount: Int, order: String, lastCardId: Long) {
        networkClient.api.libraryItemList(BaseApplication.prefs.accessToken, ReqLibraryItemList(resultCount, order, lastCardId)).start
    }

    fun libraryItemDetail(cardId: Long) {
        networkClient.api.libraryItemDetail(BaseApplication.prefs.accessToken, ReqLibraryItemDetail(cardId)).start
    }

    fun librarySalesItem(targetUid:Long, resultCount: Int, order: String, lastMarketId: Long) {
        networkClient.api.librarySalesItemList(BaseApplication.prefs.accessToken, ReqLibrarySalesItemList(targetUid, resultCount, order, lastMarketId)).start
    }

    fun librarySearchItem(search: String, resultCount: Int, order: String, lastCardId: Long) {
        networkClient.api.librarySearchItem(BaseApplication.prefs.accessToken,  ReqLibrarySearchItem(search, resultCount, order, lastCardId)).start
    }

    fun libraryCollectionList(targetUid: Long, sportsType: String, resultCount: Int, order: String, lastCardId: Long) {
        networkClient.api.libraryCollectionList(BaseApplication.prefs.accessToken, ReqLibraryCollectionList(targetUid, sportsType, resultCount, order, lastCardId)).start
    }

    fun marketRegister(cardId: Long, count: Int, price: Int) {
        networkClient.api.marketRegister(BaseApplication.prefs.accessToken, ReqMarketRegister(cardId, count, price)).start
    }

    fun marketSalesList(sportsType: String, resultCount: Int, order: String, lastMarketId: Long) {
        networkClient.api.marketSales(BaseApplication.prefs.accessToken, ReqMarketSaleList(sportsType, resultCount, order, lastMarketId)).start
    }

    fun marketSaleDetail(marketId: Long) {
        networkClient.api.marketSaleDetail(BaseApplication.prefs.accessToken, ReqMarketSaleDetail(marketId)).start
    }

    fun marketNewArrivalList(resultCount: Int, order: String, lastMarketId: Long) {
        networkClient.api.marketNewArrival(BaseApplication.prefs.accessToken, ReqMarketNewArrivalList(resultCount, order, lastMarketId)).start
    }

    fun cardList(sportsId: String, count: Int, sortOrder: Int, lastId: Long) {
        networkClient.api.cardList(BaseApplication.prefs.accessToken, ReqCardList(sportsId, count, sortOrder, lastId)).start
    }

    fun bcTokensOwned() {
//        networkClient.api.bcTokensOwned(BaseApplication.prefs.accessToken).start
    }

    fun cardDetail(tokenId: Int?) {
        networkClient.api.cardDetail(BaseApplication.prefs.accessToken, ReqTokenCardDetail(tokenId)).start
    }
}