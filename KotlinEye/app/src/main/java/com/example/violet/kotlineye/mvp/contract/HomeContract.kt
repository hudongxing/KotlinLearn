package com.example.violet.kotlineye.mvp.contract

import com.example.violet.kotlineye.base.BasePresenter
import com.example.violet.kotlineye.base.BaseView
import com.example.violet.kotlineye.mvp.model.bean.HomeBean

/**
 * Created by violet.
 * @ author VioLet.
 * @ email 286716191@qq.com
 * @ date 2017/11/10.
 */
interface HomeContract{
    interface View : BaseView<Presenter> {
        fun setData(bean : HomeBean)
    }
    interface Presenter : BasePresenter {
        fun requestData()
    }
}