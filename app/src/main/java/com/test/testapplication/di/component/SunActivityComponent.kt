package com.test.testapplication.di.component

import com.test.testapplication.di.module.SunActivityModule
import com.test.testapplication.di.scope.SunScope
import com.test.testapplication.ui.SunActivity
import dagger.Subcomponent


/**
 * @author Ruslan Stosyk
 * Date: May, 11, 2019
 * Time: 10:46
 */

@Subcomponent(modules = [SunActivityModule::class])
@SunScope
interface SunActivityComponent {

    fun inject(activity: SunActivity)
}