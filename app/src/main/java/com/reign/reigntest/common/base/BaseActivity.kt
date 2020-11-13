package com.reign.reigntest.common.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.reign.reigntest.R

open class BaseActivity: AppCompatActivity() {
    lateinit var fm: FragmentManager

    open fun pushFragment(fragment: Fragment) {
        pushFragment(fragment, R.id.container, false)
    }

    open fun pushFragmentWithBackStack(fragment: Fragment) {
        pushFragment(fragment, R.id.container, true)
    }

    open fun pushFragment(fragment: Fragment, container: Int, addBackStack: Boolean, vararg animations: Int) {
        val transaction: FragmentTransaction = fm.beginTransaction()
        val tag = fragment.javaClass.simpleName
        if (addBackStack) {
            transaction.addToBackStack(tag)
        }
        when (animations.size) {
            0 -> transaction.setCustomAnimations(
                    R.anim.push_show_in_simple,
                    R.anim.push_hidden_out_simple,
                    0,
                    0)
            1 -> {
            }
            2 -> transaction.setCustomAnimations(animations[0], animations[1])
            4 -> transaction.setCustomAnimations(animations[0], animations[1], animations[2], animations[3])
            else -> throw RuntimeException("Error with animations transaction")
        }
        transaction.replace(container, fragment, tag)
        try {
            transaction.commit()
        } catch (e: Exception) {
            return
        }
    }
}