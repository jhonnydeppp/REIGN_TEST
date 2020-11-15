package com.reign.reigntest.common.observer

import android.util.Log
import com.reign.reigntest.common.base.BaseErrorContract


import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class CallbackHandlingObserver<T>(private val errorApi: BaseErrorContract, private val endpoint: String) : DisposableObserver<T>() {

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(ex: Throwable) {
        Log.i("Info", "onError: ex cause-->" + ex.cause)
        Log.i("Info", "onError: ex message-->" + ex.message)
        when (ex) {
            is HttpException -> {
                val errorCode = ex.response()!!.code()
                Log.i("Info", "onError: ex-->" + ex.message())
                handleErrorCode(errorCode, endpoint)
            }
            is SocketTimeoutException -> {
                errorApi.onTimeoutError(endpoint)
            }
            is IOException -> {
                errorApi.onNetworkError(endpoint)
            }
            else -> {
                errorApi.onUnknownError(ex.message!!, endpoint)
            }
        }
        errorApi.infoError(ex.cause, ex.message)

    }


    private fun handleErrorCode(errorCode: Int, endpoint: String) {

        Log.i("Info", "handleErrorCode: codigo error $errorCode")
        if (errorCode == 500) {
            errorApi.onServerError(endpoint)
        } else if (errorCode in 400..499)
            errorApi.onBadRequestError(endpoint, errorCode)

    }

    override fun onComplete() {
        //DO NOTHING
    }


    protected abstract fun onSuccess(data: T)
}