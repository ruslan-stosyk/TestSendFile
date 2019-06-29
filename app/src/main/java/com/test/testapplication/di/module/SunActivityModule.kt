package com.test.testapplication.di.module

import com.test.testapplication.di.scope.SunScope
import com.test.testapplication.ui.SunActivityContract
import com.test.testapplication.ui.SunActivityPresenter
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull


/**
 * @author Ruslan Stosyk
 * Date: May, 11, 2019
 * Time: 10:42
 */

@Module
class SunActivityModule {

    @Provides
    @NotNull
    @SunScope
    fun provideSunActivityAction(sunActivityPresenter: SunActivityPresenter): SunActivityContract.Actions {
        return sunActivityPresenter
    }
}