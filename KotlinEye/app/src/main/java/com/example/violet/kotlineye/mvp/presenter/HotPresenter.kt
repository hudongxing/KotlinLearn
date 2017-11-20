package com.example.violet.kotlineye.mvp.presenter

import android.content.Context
import com.example.violet.kotlineye.mvp.contract.HotContract
import com.example.violet.kotlineye.mvp.model.HotModel
import com.example.violet.kotlineye.mvp.model.bean.HotBean
import com.example.violet.kotlineye.utils.applySchedulers
import io.reactivex.Observable

/**
 * Created by violet.
 * @ author VioLet.
 * @ email 286716191@qq.com
 * @ date 2017/11/14.
 */
class HotPresenter(context: Context,view: HotContract.View) : HotContract.Presenter{


    var mContext : Context? = null
    var mView : HotContract.View? = null
    val mModel : HotModel by lazy {
        HotModel()
    }
    init {
        mView = view
        mContext = context
    }
    override fun start() {

    }
    override fun requestData(strategy: String) {
        val observable : Observable<HotBean>? = mContext?.let { mModel.loadData(mContext!!,strategy) }
        observable?.applySchedulers()?.subscribe { bean : HotBean ->
            mView?.setData(bean)
        }
    }

}