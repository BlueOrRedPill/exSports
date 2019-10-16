package com.android.sportnmedc.exdata.user

import com.android.sportnmedc.R
import com.android.sportnmedc.exdata.BaseDataSource
import java.util.*

class ExUserSource:BaseDataSource<ExUserModel>{


    private var mCurrentProfile:ExUserModel = ExUserModel("Ekachai Sungsup",R.drawable.ex_profile_image,Random().nextInt(1000),Random().nextInt(200),Random().nextInt(10))

    private var mList:ArrayList<ExUserModel> = ArrayList()


    fun getCurrentProfile():ExUserModel{
        return mCurrentProfile
    }

    override fun getDataList(): List<ExUserModel> {
      return mList
    }

    override fun getDataIndex(index:Int): ExUserModel? {
      return mList[index]
    }


    override fun load(onLoaded: BaseDataSource.OnLoad) {
        loadFromDB()
        onLoaded.onLoaded()
    }

    override fun loadFromNetwork() {

    }

    override fun loadFromDB() {
        if (mList.isEmpty()){
            mList.add(ExUserModel("Tony",R.drawable.ex_profile_image_list,Random().nextInt(1000),Random().nextInt(200),Random().nextInt(10)))
            mList.add(ExUserModel("Gustavo",R.drawable.ex_profile_image_list,Random().nextInt(1000),Random().nextInt(200),Random().nextInt(10)))
            mList.add(ExUserModel("Ekachai sungsup",R.drawable.ex_profile_image,Random().nextInt(1000),Random().nextInt(200),Random().nextInt(10)))
            mList.add(ExUserModel("Leo",R.drawable.ex_profile_image_list,Random().nextInt(1000),Random().nextInt(200),Random().nextInt(10)))
            mList.add(ExUserModel("Micheal",R.drawable.ex_profile_image_list,Random().nextInt(1000),Random().nextInt(200),Random().nextInt(10)))
            mList.add(ExUserModel("David",R.drawable.ex_profile_image_list,Random().nextInt(1000),Random().nextInt(200),Random().nextInt(10)))
            mList.add(ExUserModel("Salah",R.drawable.ex_profile_image_list,Random().nextInt(1000),Random().nextInt(200),Random().nextInt(10)))
            mList.add(ExUserModel("Muhammed",R.drawable.ex_profile_image_list,Random().nextInt(1000),Random().nextInt(200),Random().nextInt(10)))
            mList.add(ExUserModel("Enriko",R.drawable.ex_profile_image_list,Random().nextInt(1000),Random().nextInt(200),Random().nextInt(10)))
            mList.add(ExUserModel("Ronaldo",R.drawable.ex_profile_image_list,Random().nextInt(1000),Random().nextInt(200),Random().nextInt(10)))
            mList.add(ExUserModel("William",R.drawable.ex_profile_image_list,Random().nextInt(1000),Random().nextInt(200),Random().nextInt(10)))

        }
    }





}