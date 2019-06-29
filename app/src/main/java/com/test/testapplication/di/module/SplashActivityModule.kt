package com.test.testapplication.di.module

import com.test.testapplication.di.scope.SplashScope
import com.test.testapplication.ui.splash.SplashActivityContract.Actions
import com.test.testapplication.ui.splash.SplashActivityPresenter
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull


/**
 * @author Ruslan Stosyk
 * Date: June, 29, 2019
 * Time: 15:16
 */
 
@Module
class SplashActivityModule{

    @Provides
    @NotNull
    @SplashScope
    fun provideSplashActivityAction(splashActivityPresenter: SplashActivityPresenter): Actions {
        return splashActivityPresenter
    }
}