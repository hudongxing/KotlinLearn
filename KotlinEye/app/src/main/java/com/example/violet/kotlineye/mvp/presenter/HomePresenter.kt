package com.example.violet.kotlineye.mvp.presenter

import android.content.Context
import com.example.violet.kotlineye.mvp.contract.HomeContract
import com.example.violet.kotlineye.mvp.model.HomeModel
import com.example.violet.kotlineye.mvp.model.bean.HomeBean
import com.example.violet.kotlineye.utils.applySchedulers
import io.reactivex.Observable

/**
 * Created by violet.
 * @ author VioLet.
 * @ email 286716191@qq.com
 * @ date 2017/11/10.
 */
class HomePresenter(context:Context,view :HomeContract.View):HomeContract.Presenter{

    var mContext : Context? = null
    var mView : HomeContract.View? = null
    val mModel : HomeModel by lazy {
        HomeModel()
    }

    init {
        mView = view
        mContext = context
    }
    override fun start() {
        requestData()
    }

    override fun requestData() {

        val observable : Observable<HomeBean>? = mContext?.let { mModel.loadData(it,true,"0") }
        observable?.applySchedulers()?.subscribe { homeBean : HomeBean ->
            mView?.setData(homeBean)
        }
    }

    fun moreData(string : String?){
        val observable : Observable<HomeBean>? = mContext?.let { mModel.loadData(it,false,string) }
        observable?.applySchedulers()?.subscribe{ homeBean ->
            mView?.setData(homeBean)
        }
    }

}