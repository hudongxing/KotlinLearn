package com.example.violet.kotlineye.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.violet.kotlineye.R
import com.example.violet.kotlineye.adapter.RankAdapter
import com.example.violet.kotlineye.mvp.contract.FindContract
import com.example.violet.kotlineye.mvp.contract.FindDetailContract
import com.example.violet.kotlineye.mvp.model.bean.FindBean
import com.example.violet.kotlineye.mvp.model.bean.HotBean
import com.example.violet.kotlineye.mvp.presenter.FindDetailPresenter
import kotlinx.android.synthetic.main.activity_find_detail.*
import java.util.regex.Pattern

/**
 * Created by violet.
 * @ author VioLet.
 * @ email 286716191@qq.com
 * @ date 2017/11/16.
 */
class FindDetailActivity : AppCompatActivity(), FindDetailContract.View{
    lateinit var mPresenter: FindDetailPresenter
    lateinit var name: String
    lateinit var mAdapter : RankAdapter
    lateinit var data: String
    var mIsRefresh: Boolean = false
    var mList: ArrayList<HotBean.ItemListBean.DataBean> = ArrayList()
    var mstart: Int = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_detail)
        setToolbar()
        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = RankAdapter(this, mList)
        recyclerView.adapter = mAdapter
        refreshLayout.setOnRefreshListener { onRefresh() }
        mPresenter = FindDetailPresenter(this, this)
        mPresenter.requestData(name, "date")
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                var layoutManager : LinearLayoutManager = recyclerView?.layoutManager as LinearLayoutManager
                var lastPositon = layoutManager.findLastVisibleItemPosition()
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastPositon == mList.size - 1){
                    if (data != null) {
                        mPresenter?.requesMoreData(mstart, name, "date")
                        mstart = mstart.plus(10)
                    }
                }
            }
        })
    }


    override fun setData(bean: HotBean) {
        val regEx = "[^0-9]"
        val p = Pattern.compile(regEx)
        val m = p.matcher(bean.nextPageUrl as CharSequence?)
        data = m.replaceAll("").subSequence(1, m.replaceAll("").length - 1).toString()
        if (mIsRefresh) {
            mIsRefresh = false
            refreshLayout.isRefreshing = false
            if (mList.size > 0) {
                mList.clear()
            }

        }
        bean.itemList?.forEach {
            it.data?.let { it1 -> mList.add(it1) }
        }
        mAdapter.notifyDataSetChanged()
    }

    private fun onRefresh(){
        if (!mIsRefresh) {
            mIsRefresh = true
            mPresenter.requestData(name, "date")
        }
    }

    private fun setToolbar(){
        setSupportActionBar(toolbar)
        var bar = supportActionBar
        intent.getStringExtra("name")?.let {
            name = intent.getStringExtra("name")
            bar?.title = name
        }

        bar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setOnClickListener { onBackPressed()}
    }

}