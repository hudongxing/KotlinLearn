package com.example.violet.kotlineye.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.violet.kotlineye.R
import com.example.violet.kotlineye.adapter.RankAdapter
import com.example.violet.kotlineye.mvp.contract.HotContract
import com.example.violet.kotlineye.mvp.model.bean.HotBean
import com.example.violet.kotlineye.mvp.presenter.HotPresenter
import kotlinx.android.synthetic.main.rank_fragment.*

/**
 * Created by violet.
 * @ author VioLet.
 * @ email 286716191@qq.com
 * @ date 2017/11/10.
 */
class RankFragment : BaseFragment(), HotContract.View{

    lateinit var mPresenter: HotPresenter
    lateinit var mStrategy: String
    lateinit var mAdapter: RankAdapter
    var mList: ArrayList<HotBean.ItemListBean.DataBean> = ArrayList()
    override fun setData(bean: HotBean) {
        Log.e("rank", bean.toString())
        if(mList.size>0){
            mList.clear()
        }
        bean.itemList?.forEach {
            it.data?.let { it1 -> mList.add(it1) }
        }
        mAdapter.notifyDataSetChanged()
    }

    override fun getLayoutResources(): Int {
        return R.layout.rank_fragment
    }

    override fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = RankAdapter(context, mList)
        recyclerView.adapter = mAdapter
        if (arguments != null) {
            mStrategy = arguments.getString("strategy")
            mPresenter = HotPresenter(context, this)
            mPresenter.requestData(mStrategy)
        }

    }

}