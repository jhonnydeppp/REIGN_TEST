package com.reign.reigntest.ui.hits

import com.reign.reigntest.common.RetrofitClient
import com.reign.reigntest.common.api.HitRestApi
import com.reign.reigntest.common.models.HitItem
import com.reign.reigntest.common.utils.Constants
import com.reign.reigntest.common.utils.data.PrefsUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class HitModel {
    private val api = RetrofitClient.getClient().create(HitRestApi::class.java)

    fun getHits() = api.getHits(Constants.QUERY_ANDROID).subscribeOn(
        Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())!!

    fun saveLastList(hitsList: ArrayList<HitItem>) {
        filterDeleteListElements(hitsList)
    }

    fun deleteElement(elementToDelete: HitItem): ArrayList<HitItem> {
        val list = PrefsUtil.hitsList
        val deleteElementsList = PrefsUtil.deletedElementsList
        deleteElementsList.add(elementToDelete)
        PrefsUtil.deletedElementsList = deleteElementsList
        list.remove(elementToDelete)
        PrefsUtil.hitsList = list
        return list
    }

    fun getLocalHitList(): ArrayList<HitItem> {
        return PrefsUtil.hitsList
    }

    private fun filterDeleteListElements(hitsList: ArrayList<HitItem>) {
        val deleteElementsList = PrefsUtil.deletedElementsList
        hitsList.removeAll(deleteElementsList)
        PrefsUtil.hitsList = hitsList
    }
}