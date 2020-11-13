package com.reign.reigntest.common.base

interface BaseErrorContract {

    fun onUnknownError(error: String, caller: String)

    fun onTimeoutError(caller: String)

    fun onNetworkError(caller: String)

    fun onBadRequestError(caller: String, codeError: Int)

    fun onServerError(caller: String)

    fun infoError(cause: Throwable?, msg: String?)
}