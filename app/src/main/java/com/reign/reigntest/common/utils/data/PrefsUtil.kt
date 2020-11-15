package com.reign.reigntest.common.utils.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.reign.reigntest.common.models.HitItem

private const val REIGN_PREF = "com.reign.reigntest.REIGN_PREF"
private const val LAST_HITS_LIST = "LAST_HITS_LIST"
private const val DELETE_ELEMENTS_LIST = "DELETE_ELEMENTS_LIST"

object PrefsUtil {

    private val TAG = javaClass.simpleName
    private lateinit var mPreferences: SharedPreferences
    private const val MODE = Context.MODE_PRIVATE

    fun init(context: Context) {
        mPreferences = context.getSharedPreferences(REIGN_PREF, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var hitsList: ArrayList<HitItem>
        get(): ArrayList<HitItem> {
            var list: ArrayList<HitItem> = ArrayList<HitItem>()
            val json: String? = mPreferences.getString(LAST_HITS_LIST, "")
            if (!json.isNullOrEmpty()) {
                val type = object : TypeToken<ArrayList<HitItem>>() {}.type
                list = Gson().fromJson<ArrayList<HitItem>>(json, type)
            }
             return list
        }

        set(hitsList) = mPreferences.edit { pref->
            try {
                val gson = Gson()
                val json = gson.toJson(hitsList)
                pref.putString(LAST_HITS_LIST, json)
            } catch (e: Exception) {
                Log.e(TAG, "hitsList: preferences ", e)
            }
        }

    var deletedElementsList: ArrayList<HitItem>
        get(): ArrayList<HitItem> {
            var list: ArrayList<HitItem> = ArrayList<HitItem>()
            val json: String? = mPreferences.getString(DELETE_ELEMENTS_LIST, "")
            if (!json.isNullOrEmpty()) {
                val type = object : TypeToken<ArrayList<HitItem>>() {}.type
                list = Gson().fromJson<ArrayList<HitItem>>(json, type)
            }
            return list
        }

        set(deletedElementsList) = mPreferences.edit { pref->
            try {
                val gson = Gson()
                val json = gson.toJson(deletedElementsList)
                pref.putString(DELETE_ELEMENTS_LIST, json)
            } catch (e: Exception) {
                Log.e(TAG, "deletedElementsList: preferences ", e)
            }
        }
}