package com.example.violet.kotlineye.mvp.model

import android.content.Context
import com.example.violet.kotlineye.mvp.model.bean.HotBean
import com.example.violet.kotlineye.network.ApiService
import com.example.violet.kotlineye.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by violet.
 * @ author VioLet.
 * @ email 286716191@qq.com
 * @ date 2017/11/16.
 */
class FindDetailModel{
    fun  loadData(context: Context, categoryName: String, strategy: String?) : Observable<HotBean>? {
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        var apiServer = retrofitClient.create(ApiService::class.java)
        return apiServer?.getFindDetailData(categoryName, strategy!!, "26868b32e808498db32fd51fb422d00175e179df", 83)
    }

    fun loadMoreData(context: Context,start : Int, categoryName: String, strategy: String?): Observable<HotBean>? {
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService = retrofitClient.create(ApiService::class.java)
        return apiService?.getFindDetailMoreData(start,10,categoryName, strategy!!)
    }
}