package com.test.testapplication.di.component

import com.test.testapplication.App
import com.test.testapplication.di.module.AppModule
import com.test.testapplication.di.module.InteractorModule
import com.test.testapplication.di.module.NetModule
import com.test.testapplication.di.module.UseCaseModule
import dagger.Component
import javax.inject.Singleton


/**
 * @author Ruslan Stosyk
 * Date: May, 11, 2019
 * Time: 1:11
 */

@Singleton
@Component(modules = [AppModule::class, UseCaseModule::class, InteractorModule::class, NetModule::class])
interface AppComponent {
    fun inject(app: App)
}