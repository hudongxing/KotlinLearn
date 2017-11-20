package com.example.violet.kotlineye.ui.fragment

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AbsListView
import com.example.violet.kotlineye.R
import com.example.violet.kotlineye.adapter.HomeAdatper
import com.example.violet.kotlineye.mvp.contract.HomeContract
import com.example.violet.kotlineye.mvp.model.bean.HomeBean
import com.example.violet.kotlineye.mvp.model.bean.HomeBean.IssueListBean.ItemListBean
import com.example.violet.kotlineye.mvp.presenter.HomePresenter
import kotlinx.android.synthetic.main.home_fragment.*
import java.util.regex.Pattern

/**
 * Created by violet.
 * @ author VioLet.
 * @ email 286716191@qq.com
 * @ date 2017/11/10.
 */
class HomeFragment : BaseFragment(),HomeContract.View, SwipeRefreshLayout.OnRefreshListener{
    var mIsRefresh: Boolean = false
    var mPresenter: HomePresenter? = null
    var mList = ArrayList<ItemListBean>()
    var mAdapter: HomeAdatper? = null
    var data: String? = null

    override fun getLayoutResources(): Int {
        return R.layout.home_fragment
    }

    override fun initView() {
        mPresenter = HomePresenter(context, this)
        mPresenter?.start()
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = HomeAdatper(context, mList)
        recyclerView.adapter = mAdapter
        refreshLayout.setOnRefreshListener(this)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var layoutManager: LinearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
                var lastPositon = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPositon == mList.size - 1) {
                    if (data != null) {
                        mPresenter?.moreData(data)
                    }

                }
            }
        })
    }

    override fun setData(bean: HomeBean) {
        val regEx = "[^0-9]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(bean?.nextPageUrl)
        data = m.replaceAll("").subSequence(1, m.replaceAll("").length - 1).toString()
        if (mIsRefresh) {
            mIsRefresh = false
            refreshLayout.isRefreshing = false
            if (mList.size > 0) {
                mList.clear()
            }

        }
        bean.issueList!!
                .flatMap { it.itemList!! }
                .filter { it.type.equals("video") }
                .forEach { mList.add(it) }
        mAdapter?.notifyDataSetChanged()
    }

    override fun onRefresh() {

        if (!mIsRefresh) {
            mIsRefresh = true
            mPresenter?.start()
        }
    }

}