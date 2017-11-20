package com.example.violet.kotlineye.mvp.presenter

import android.content.Context
import com.example.violet.kotlineye.mvp.contract.FindDetailContract
import com.example.violet.kotlineye.mvp.model.FindDetailModel
import com.example.violet.kotlineye.mvp.model.bean.HotBean
import com.example.violet.kotlineye.ui.FindDetailActivity
import com.example.violet.kotlineye.utils.applySchedulers
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by violet.
 * @ author VioLet.
 * @ email 286716191@qq.com
 * @ date 2017/11/16.
 */
class FindDetailPresenter(context: Context, view: FindDetailActivity) : FindDetailContract.Presenter{

    var mContext : Context? = null
    var mView :  FindDetailContract.View? = null
    val mModel: FindDetailModel by lazy {
        FindDetailModel()
    }

    init {
        mContext = context
        mView = view
    }


    override fun start() {
    }

    override fun requestData(categoryName: String, strategy: String) {

        val observable: Observable<HotBean>? = mContext?.let {  mModel.loadData(mContext!!, categoryName, strategy)  }
        observable?.subscribeOn(Schedulers.io())!!.
                unsubscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe{ it ->
                        mView?.setData(it)
                }


    }


    fun requesMoreData(start: Int, categoryName: String, strategy: String){

        val observable: Observable<HotBean>? = mContext?.let { mModel.loadMoreData(mContext!!, start, categoryName, strategy) }
        observable?.applySchedulers()?.subscribe { bean: HotBean ->
            mView?.setData(bean)
        }

    }

}

