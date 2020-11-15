package com.reign.reigntest

import android.os.Bundle
import com.reign.reigntest.common.base.BaseActivity
import com.reign.reigntest.common.utils.data.PrefsUtil
import com.reign.reigntest.ui.hits.HitsViewFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PrefsUtil.init(applicationContext)
        fm = supportFragmentManager
        pushFragment(HitsViewFragment.newInstance(this))
    }
}