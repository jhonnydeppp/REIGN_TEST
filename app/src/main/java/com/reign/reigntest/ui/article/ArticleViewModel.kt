package com.reign.reigntest.ui.article

import androidx.lifecycle.ViewModel
import com.reign.reigntest.common.base.BaseErrorContract

class ArticleViewModel: ViewModel(), BaseErrorContract{

    override fun onUnknownError(error: String, caller: String) {
        TODO("Not yet implemented")
    }

    override fun onTimeoutError(caller: String) {
        TODO("Not yet implemented")
    }

    override fun onNetworkError(caller: String) {
        TODO("Not yet implemented")
    }

    override fun onBadRequestError(caller: String, codeError: Int) {
        TODO("Not yet implemented")
    }

    override fun onServerError(caller: String) {
        TODO("Not yet implemented")
    }

    override fun infoError(cause: Throwable?, msg: String?) {
        TODO("Not yet implemented")
    }
}