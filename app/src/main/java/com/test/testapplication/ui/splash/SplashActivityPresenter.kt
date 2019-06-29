package com.test.testapplication.ui.splash

import android.content.Context
import com.test.testapplication.base.BaseActivityPresenter
import com.test.testapplication.ui.splash.SplashActivityContract.Actions
import com.test.testapplication.ui.splash.SplashActivityContract.Views
import javax.inject.Inject

/**
 * @author Ruslan Stosyk
 * Date: June, 29, 2019
 * Time: 15:06
 */

class SplashActivityPresenter @Inject
internal constructor(appContext: Context) : BaseActivityPresenter<Views>(appContext), Actions {

    override fun initCurrentLocation() {

    }
}
