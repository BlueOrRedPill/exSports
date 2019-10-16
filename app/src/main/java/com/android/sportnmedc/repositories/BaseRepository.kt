package com.android.sportnmedc.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.sportnmedc.BaseApplication
import com.android.sportnmedc.network.NetworkClient
import com.android.sportnmedc.network.NewBaseResponse
import com.orhanobut.logger.Logger
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


abstract class BaseRepository<T> {

    protected val networkClient = NetworkClient.getInstance()

    private val mValueLiveData: MutableLiveData<T> = MutableLiveData()

    private val mErrorLiveData: MutableLiveData<String> = MutableLiveData()

    private val mLoadingIndicator: MutableLiveData<Boolean> = MutableLiveData()

    /*** For use on ViewModel ***/
    fun getValueLiveData(): LiveData<T> = mValueLiveData

    fun getErrorLiveData(): LiveData<String> = mErrorLiveData

    fun getIndicatorLiveData(): LiveData<Boolean> = mLoadingIndicator
    /*** END ***/

    protected val <K : NewBaseResponse<*>> Single<K>.start: Disposable
        get() = run {
            subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    Logger.d("doOnSubscribe")
                    mLoadingIndicator.postValue(true)
                }
                .doFinally {
                    Logger.d("doFinally")
                    mLoadingIndicator.postValue(false)
                }
                .subscribe({ response ->
                    Logger.json(response.toString())
                    responseHandler(response)
                }, { throwable ->
                    Logger.d(throwable.message)
                    responseHandler(null, throwable.message ?: "")
                })
        }

    private fun <K : NewBaseResponse<*>> responseHandler(response: K?, errorMessage: String = "") {
        response?.also {
            if (it.isSuccess()) {
                it.result.refreshToken?.also {
                    BaseApplication.prefs.refreshToken = it
                }
                it.result.accessToken?.also {
                    BaseApplication.prefs.accessToken = it
                }
                Logger.json(it.data.toString())
                successResponse(it.data as T?)
            } else {
                when {
                    response.warnings.isNullOrEmpty() -> onError(response.result.message)
                    else -> errorResponse(it.status.toInt(), response.warnings.toString())
                }
            }
        } ?: errorResponse(500, errorMessage)

    }

    private fun successResponse(data: T?) {
        data?.also {
            mValueLiveData.postValue(data)
        } ?: onError("Data Empty")

    }

    private fun onError(message: String) {
        mErrorLiveData.postValue(message)
    }

    private fun errorResponse(statusCode: Int, message: String = "") {
        when (statusCode) {
            in 300..399 -> {
                onError("Redirection")
            }
            in 400..499 -> {
                onError("Client Error ${":$message"}")
            }
            in 500..599 -> {
                onError("Server Error ${":$message"}")
            }
        }
    }

    fun setLocalErrorMessage(message: String) {
        onError(message)
    }

}