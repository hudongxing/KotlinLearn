package com.example.violet.kotlineye.mvp.model

import android.content.Context
import com.example.violet.kotlineye.mvp.model.bean.HomeBean
import com.example.violet.kotlineye.network.ApiService
import com.example.violet.kotlineye.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by violet.
 * @ author VioLet.
 * @ email 286716191@qq.com
 * @ date 2017/11/10.
 */
class HomeModel{
    fun loadData(context: Context, isFirst: Boolean, data: String?): Observable<HomeBean>? {
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService  = retrofitClient.create(ApiService::class.java)
        when(isFirst) {
            true -> return apiService?.getHomeData()

            false -> return apiService?.getHomeMoreData(data.toString(), "2")
        }
    }
}