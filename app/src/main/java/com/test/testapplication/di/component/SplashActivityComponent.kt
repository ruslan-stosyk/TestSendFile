package com.test.testapplication.di.component

import com.test.testapplication.di.module.SplashActivityModule
import com.test.testapplication.di.scope.SplashScope
import com.test.testapplication.ui.splash.SplashActivity
import dagger.Subcomponent


/**
 * @author Ruslan Stosyk
 * Date: June, 29, 2019
 * Time: 15:16
 */

@Subcomponent(modules = [SplashActivityModule::class])
@SplashScope
interface SplashActivityComponent {

    fun inject(activity: SplashActivity)
}