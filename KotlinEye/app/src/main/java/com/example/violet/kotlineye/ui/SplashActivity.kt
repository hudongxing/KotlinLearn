package com.example.violet.kotlineye.ui


import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import com.example.violet.kotlineye.MainActivity
import com.example.violet.kotlineye.R
import com.example.violet.kotlineye.utils.newIntent
import kotlinx.android.synthetic.main.activity_splash.*


/**
 * Created by violet.
 * @ author VioLet.
 * @ email 286716191@qq.com
 * @ date 2017/11/9.
 */
class SplashActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置全屏
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        initView()
        setAnimation()

    }

    private fun initView(){
        val font : Typeface = Typeface.createFromAsset(this.assets,"fonts/Lobster-1.4.otf")
        tv_name_english.typeface = font
        tv_english_intro.typeface = font

    }

    private fun setAnimation(){
        val alphaAnimation = AlphaAnimation(0.1f,1.0f)
        alphaAnimation.duration = 1000
        val scaleAnimation = ScaleAnimation(0.1f,1.0f,0.1f,1.0f,ScaleAnimation.RELATIVE_TO_SELF,0.5f, ScaleAnimation.RELATIVE_TO_SELF,0.5f)
        scaleAnimation.duration =1000
        val animationSet = AnimationSet(true)
        animationSet.addAnimation(alphaAnimation)
        animationSet.addAnimation(scaleAnimation)
        animationSet.duration = 1000
        iv_icon_splash.startAnimation(animationSet)
        animationSet.setAnimationListener(object : Animation.AnimationListener{

            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
//                var intent = Intent()
//                intent.setClass(applicationContext,MainActivity::class.java)
//                startActivity(intent)

                newIntent<MainActivity>()
                finish()
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })

    }
}