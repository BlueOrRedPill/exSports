package com.android.sportnmedc.repositories

import com.android.sportnmedc.BaseApplication
import com.android.sportnmedc.network.model.CollectionTypeModel
import com.android.sportnmedc.network.request.ReqLibraryCollectionType
import com.google.gson.JsonObject

class CollectionRepository<T> :BaseRepository<T>(){

    fun libraryCollectionType(targetUid: Long) {
        networkClient.api.libraryCollectionType(BaseApplication.prefs.accessToken ,ReqLibraryCollectionType(targetUid)).start
    }

    fun sportsList() {
        networkClient.api.sportsList(BaseApplication.prefs.accessToken).start
    }
}