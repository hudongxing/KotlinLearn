package com.example.violet.kotlineye.mvp.model

import android.content.Context
import com.example.violet.kotlineye.mvp.model.bean.FindBean
import com.example.violet.kotlineye.network.ApiService
import com.example.violet.kotlineye.network.RetrofitClient
import io.reactivex.Observable

/**
 * Created by violet.
 * @ author VioLet.
 * @ email 286716191@qq.com
 * @ date 2017/11/13.
 */

class FindModel{
    fun loadData(context: Context): Observable<MutableList<FindBean>>? {
        val retrofitClient = RetrofitClient.getInstance(context, ApiService.BASE_URL)
        val apiService = retrofitClient.create(ApiService::class.java)
        return apiService?.getFindData()
    }
}