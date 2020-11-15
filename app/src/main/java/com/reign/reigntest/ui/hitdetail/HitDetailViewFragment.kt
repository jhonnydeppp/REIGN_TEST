package com.reign.reigntest.ui.hitdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.reign.reigntest.R
import com.reign.reigntest.databinding.FragmentHitDetailViewBinding

private const val URL = "url"

class HitDetailViewFragment : Fragment() {
    private var url: String? = null
    lateinit var mFragmentHitDetailViewBinding: FragmentHitDetailViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mFragmentHitDetailViewBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_hit_detail_view, container, false
        )
        mFragmentHitDetailViewBinding.lifecycleOwner = this
        url?.let {  url -> launchWebView(url) }
        return mFragmentHitDetailViewBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(url: String) =
            HitDetailViewFragment().apply {
                arguments = Bundle().apply {
                    putString(URL, url)
                }
            }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun launchWebView(url: String) {
        mFragmentHitDetailViewBinding.wvHit.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                // loading while charge url
                return super.shouldOverrideUrlLoading(view, url)
            }
        }

        mFragmentHitDetailViewBinding.wvHit.setInitialScale(1)
        mFragmentHitDetailViewBinding.wvHit.settings.loadWithOverviewMode = true
        mFragmentHitDetailViewBinding.wvHit.settings.useWideViewPort = true
        mFragmentHitDetailViewBinding.wvHit.settings.loadsImagesAutomatically = true
        mFragmentHitDetailViewBinding.wvHit.settings.javaScriptEnabled = true
        mFragmentHitDetailViewBinding.wvHit.settings.builtInZoomControls = false
        mFragmentHitDetailViewBinding.wvHit.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        mFragmentHitDetailViewBinding.wvHit.settings.setSupportZoom(true)
        mFragmentHitDetailViewBinding.wvHit.settings.allowContentAccess = true
        mFragmentHitDetailViewBinding.wvHit.loadUrl(url)
    }
}