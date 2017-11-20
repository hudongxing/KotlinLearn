package com.example.violet.kotlineye.mvp.presenter

import android.content.Context
import com.example.violet.kotlineye.mvp.contract.FindContract
import com.example.violet.kotlineye.mvp.model.FindModel
import com.example.violet.kotlineye.mvp.model.bean.FindBean
import com.example.violet.kotlineye.utils.applySchedulers
import io.reactivex.Observable

/**
 * Created by violet.
 * @ author VioLet.
 * @ email 286716191@qq.com
 * @ date 2017/11/13.
 */
class FindPresenter(context: Context ,view : FindContract.View) : FindContract.Presenter{
    var mContext : Context? = null
    var mView : FindContract.View? = null
    val findModel : FindModel by lazy {
        FindModel()
    }

    init {
        mContext = context
        mView = view
    }

    override fun start() {
        requestData()
    }

    override fun requestData() {
        val observable : Observable<MutableList<FindBean>>? = mContext?.let { findModel.loadData(mContext!!) }
        observable?.applySchedulers()?.subscribe { beans : MutableList<FindBean> ->
            mView?.setData(beans)
        }
    }

}