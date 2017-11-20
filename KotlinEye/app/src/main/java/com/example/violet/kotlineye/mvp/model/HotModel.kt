package com.example.violet.kotlineye.mvp.model

import android.content.Context
import com.example.violet.kotlineye.mvp.model.bean.HotBean
import com.example.violet.kotlineye.network.ApiService
import com.example.violet.kotlineye.network.RetrofitClient

/**
 * Created by violet.
 * @ author VioLet.
 * @ email 286716191@qq.com
 * @ date 2017/11/14.
 */
class HotModel{
    fun loadData(context: Context, strategy: String?): io.reactivex.Observable<HotBean>? {
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService  = retrofitClient.create(ApiService::class.java)
        return apiService?.getHotData(10, strategy!!,"26868b32e808498db32fd51fb422d00175e179df",83)

    }
}