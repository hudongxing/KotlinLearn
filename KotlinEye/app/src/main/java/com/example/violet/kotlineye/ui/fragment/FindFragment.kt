package com.example.violet.kotlineye.ui.fragment

import android.content.Intent
import com.example.violet.kotlineye.R
import com.example.violet.kotlineye.adapter.FindAdapter
import com.example.violet.kotlineye.mvp.contract.FindContract
import com.example.violet.kotlineye.mvp.model.bean.FindBean
import com.example.violet.kotlineye.mvp.presenter.FindPresenter
import com.example.violet.kotlineye.ui.FindDetailActivity
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.find_fragment.*

/**
 * Created by violet.
 * @ author VioLet.
 * @ email 286716191@qq.com
 * @ date 2017/11/10.
 */
class FindFragment : BaseFragment(), FindContract.View{



    var mPresenter: FindPresenter? = null
    var mList : MutableList<FindBean>? = null
    var mAdapter : FindAdapter? = null


    override fun setData(beans: MutableList<FindBean>) {
        mAdapter?.mList = beans
        mList = beans
        mAdapter?.notifyDataSetChanged()
    }


    override fun getLayoutResources(): Int {
        return R.layout.find_fragment
    }

    override fun initView() {
        mPresenter = FindPresenter(context,this)
        mAdapter = FindAdapter(context,mList)
        mPresenter?.start()
        gv_find.adapter = mAdapter
        gv_find.setOnItemClickListener { parent, view, position, id ->
            var bean = mList?.get(position)
            var name = bean?.name
            Logger.i("jump FindDetailActivity")
            var intent : Intent = Intent(context, FindDetailActivity::class.java)
            intent.putExtra("name",name)
            startActivity(intent)

        }
    }

}