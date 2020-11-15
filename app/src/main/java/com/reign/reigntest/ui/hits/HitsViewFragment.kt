package com.reign.reigntest.ui.hits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.reign.reigntest.R
import com.reign.reigntest.common.base.BaseActivity
import com.reign.reigntest.common.models.HitItem
import com.reign.reigntest.common.utils.Utils
import com.reign.reigntest.databinding.FragmentHitsBinding
import com.reign.reigntest.ui.hitdetail.HitDetailViewFragment

import com.reign.reigntest.ui.hits.adapter.HitsAdapter

class HitsViewFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener, HitInterface {
    private lateinit var mHitViewModel: HitViewModel
    private lateinit var mHitsAdapter: HitsAdapter
    private lateinit var baseActivity: BaseActivity

    lateinit var mFragmentHitsBinding: FragmentHitsBinding

    companion object {
        fun newInstance(baseActivity: BaseActivity) = HitsViewFragment().apply {
            this.baseActivity = baseActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mHitViewModel = ViewModelProvider(this).get(HitViewModel::class.java)
        mFragmentHitsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_hits, container, false
        )
        mFragmentHitsBinding.lifecycleOwner = this
        recyclerHitsConfig()
        observerConfig()

        mFragmentHitsBinding.swipeLayout.setOnRefreshListener(this)
        onRefresh()
        return mFragmentHitsBinding.root
    }

    private fun observerConfig() {
        mHitViewModel.mutableHitsResponse.observe(viewLifecycleOwner,  { hits ->
            mHitsAdapter.setHitsList(hits)
            mFragmentHitsBinding.swipeLayout.isRefreshing = false
        })

        mHitViewModel.mutableMessage.observe(viewLifecycleOwner,  { message ->
            showMessage(message)
        })
    }

    private fun recyclerHitsConfig() {
        //
        context?.let { mHitsAdapter = HitsAdapter(it)}
        mHitsAdapter.setListener(this)
        mFragmentHitsBinding.rvHits.also { recycler ->
            recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recycler.adapter = mHitsAdapter
        }
    }

    override fun onRefresh() {
        mFragmentHitsBinding.swipeLayout.isRefreshing = true
        mHitViewModel.requestHits()
    }

    override fun goToWeb(url: String) {
        baseActivity.pushFragmentWithBackStack(HitDetailViewFragment.newInstance(url))
    }

    override fun showMessage(message: String) {
        mFragmentHitsBinding.swipeLayout.isRefreshing = false
        Utils.showSnackMessage(mFragmentHitsBinding.root, message)
    }

    override fun deleteElement(elementToDelete: HitItem) {
        observerConfig()
        mHitViewModel.deleteElement(elementToDelete)
    }

}