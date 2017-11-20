package com.example.violet.kotlineye.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by violet.
 * @ author VioLet.
 * @ email 286716191@qq.com
 * @ date 2017/11/13.
 */
object NetworkUtils{

    fun isNetConneted(context: Context):Boolean{
        val connectManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo : NetworkInfo?= connectManager.activeNetworkInfo
        if(networkInfo==null){
            return  false
        }else{
            return networkInfo.isAvailable&& networkInfo.isConnected
        }

    }

    fun isNetworkConnected(context: Context,typeMoblie : Int): Boolean{
        if(!isNetConneted(context)){
            return false
        }
        val connectManager  = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo : NetworkInfo = connectManager.getNetworkInfo(typeMoblie)
        if(networkInfo==null){
            return false;
        }else{
            return  networkInfo.isConnected && networkInfo.isAvailable
        }
    }

    fun isPhoneNetConnected(context: Context): Boolean {
        val typeMobile = ConnectivityManager.TYPE_MOBILE
        return isNetworkConnected(context,typeMobile)
    }

    fun isWifiNetConnected(context: Context) : Boolean{
        val typeMobile = ConnectivityManager.TYPE_WIFI
        return  isNetworkConnected(context,typeMobile)
    }



}