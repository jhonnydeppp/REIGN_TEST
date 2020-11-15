package com.reign.reigntest.ui.hits

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.reign.reigntest.R
import com.reign.reigntest.common.base.BaseErrorContract
import com.reign.reigntest.common.models.HitItem
import com.reign.reigntest.common.models.HitsResponse
import com.reign.reigntest.common.observer.CallbackHandlingObserver

private const val ENDPOINT_HITS = "ENDPOINT_HITS"

class HitViewModel(application: Application): AndroidViewModel(application), BaseErrorContract{
    private val mHitModel: HitModel = HitModel()
    var mutableHitsResponse: MutableLiveData<ArrayList<HitItem>> = MutableLiveData()
    var mutableMessage: MutableLiveData<String> = MutableLiveData()
    private val TAG = javaClass.simpleName
    private val context = getApplication<Application>().applicationContext

    @SuppressLint("CheckResult")
    fun requestHits() {
        mHitModel.getHits().subscribeWith(object: CallbackHandlingObserver<HitsResponse>(this, ENDPOINT_HITS){
            override fun onSuccess(data: HitsResponse) {
                mHitModel.saveLastList(data.hits)
                mutableHitsResponse.value = mHitModel.getLocalHitList()
            }
        })
    }

    fun deleteElement(elementToDelete: HitItem) {
        mutableHitsResponse.value = mHitModel.deleteElement(elementToDelete)
    }

    private fun getLastList() {
        mutableHitsResponse.value = mHitModel.getLocalHitList()
    }

    override fun onUnknownError(error: String, caller: String) {
        getLastList()
        val completeMessageError = context.getString(R.string.on_unknown_Error).format(error,
        caller
        )
        mutableMessage.value = completeMessageError
        Log.e(TAG,completeMessageError)
    }

    override fun onTimeoutError(caller: String) {
        getLastList()
        val completeMessageError  = context.getString(R.string.on_timeout_error).format(caller)
        mutableMessage.value = completeMessageError
        Log.e(TAG,completeMessageError)
    }

    override fun onNetworkError(caller: String) {
        getLastList()
        val completeMessageError = context.getString(R.string.on_network_error).format(caller)
        mutableMessage.value = completeMessageError
        Log.e(TAG,completeMessageError)
    }

    override fun onBadRequestError(caller: String, codeError: Int) {
        getLastList()
        val completeMessageError = context.getString(R.string.on_bad_request_error).format(caller,
        codeError
        )
        mutableMessage.value = completeMessageError
        Log.e(TAG,completeMessageError)
    }

    override fun onServerError(caller: String) {
        getLastList()
        val completeMessageError = context.getString(R.string.on_server_error).format(caller)
        mutableMessage.value = completeMessageError
        Log.e(TAG,completeMessageError)
    }

    override fun infoError(cause: Throwable?, message: String?) {
        getLastList()
        val completeMessageError = context.getString(R.string.info_error).format(cause,
            message
        )
        mutableMessage.value = completeMessageError
        Log.e(TAG, completeMessageError)
    }
}