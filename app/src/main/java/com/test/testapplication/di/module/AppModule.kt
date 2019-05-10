package com.test.testapplication.di.module

import android.content.Context
import com.test.testapplication.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * @author Ruslan Stosyk
 * Date: May, 11, 2019
 * Time: 1:12
 */

@Module
class AppModule(val app: App) {

    @Provides
    @Singleton
    fun app(): App {
        return app
    }

    @Provides
    @Singleton
    fun context(): Context {
        return app.applicationContext
    }
}