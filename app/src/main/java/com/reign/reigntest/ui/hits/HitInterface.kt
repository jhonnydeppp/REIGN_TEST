package com.reign.reigntest.ui.hits

import com.reign.reigntest.common.models.HitItem
import java.util.ArrayList

interface HitInterface {

    fun goToWeb(url: String)
    fun showMessage(message: String)
    fun deleteElement(elementToDelete: HitItem)

}
