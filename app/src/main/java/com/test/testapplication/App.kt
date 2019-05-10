package com.test.testapplication

import android.app.Application
import com.test.testapplication.di.component.AppComponent
import com.test.testapplication.di.component.DaggerAppComponent
import com.test.testapplication.di.module.AppModule
import com.test.testapplication.di.module.NetModule


/**
 * @author Ruslan Stosyk
 * Date: May, 11, 2019
 * Time: 1:03
 */

class App : Application() {

    val BASE_URL = "http://localhost:3333"

    private val mAppComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .netModule(NetModule(BASE_URL))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        mAppComponent.inject(this)
    }
}