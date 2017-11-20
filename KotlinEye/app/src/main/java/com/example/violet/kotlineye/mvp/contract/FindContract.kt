package com.example.violet.kotlineye.mvp.contract

import com.example.violet.kotlineye.base.BasePresenter
import com.example.violet.kotlineye.base.BaseView
import com.example.violet.kotlineye.mvp.model.bean.FindBean

/**
 * Created by violet.
 * @ author VioLet.
 * @ email 286716191@qq.com
 * @ date 2017/11/13.
 */
interface FindContract {
    interface View : BaseView<Presenter>{
        fun setData(beans : MutableList<FindBean>)
    }

    interface Presenter : BasePresenter {
        fun requestData()
    }
}