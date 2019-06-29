package com.test.testapplication

import android.app.Application
import com.test.testapplication.di.component.AppComponent
import com.test.testapplication.di.component.DaggerAppComponent
import com.test.testapplication.di.component.SplashActivityComponent
import com.test.testapplication.di.component.SunActivityComponent

import com.test.testapplication.di.module.AppModule
import com.test.testapplication.di.module.NetModule
import com.test.testapplication.di.module.SplashActivityModule
import com.test.testapplication.di.module.SunActivityModule


/**
 * @author Ruslan Stosyk
 * Date: May, 11, 2019
 * Time: 1:03
 */

class App : Application() {

    val BASE_URL = "http://localhost:3333"
    private var mSplashActivityComponent: SplashActivityComponent? = null
    private var mSunActivityComponent: SunActivityComponent? = null

    private val mAppComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .netModule(NetModule(BASE_URL))
            .build()
    }

    fun getSplashActivityComponent():SplashActivityComponent {
        if (mSplashActivityComponent == null) {
            mSplashActivityComponent = mAppComponent.addSplashActivityComponent(SplashActivityModule())
        }
        return mSplashActivityComponent!!
    }

    fun getSunActivityComponent(): SunActivityComponent {
        if (mSunActivityComponent == null) {
            mSunActivityComponent = mAppComponent.addSunActivityComponent(SunActivityModule())
        }
        return mSunActivityComponent!!
    }

    fun releaseSplashActivityComponent() {
        mSplashActivityComponent = null
    }

    fun releaseSunActivityComponent() {
        mSunActivityComponent = null
    }
}