package com.test.testapplication.ui.splash

import android.os.Bundle
import com.test.testapplication.App
import com.test.testapplication.base.BaseActivity
import com.test.testapplication.ui.splash.SplashActivityContract.Actions
import com.test.testapplication.ui.splash.SplashActivityContract.Views

/**
 * @author Ruslan Stosyk
 * Date: June, 29, 2019
 * Time: 15:03
 */

class SplashActivity : BaseActivity<Actions>(), Views {
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).getSplashActivityComponent().inject(this)
        super.onCreate(savedInstanceState)
        actions.initCurrentLocation()
    }

    override fun onDestroy() {
        (application as App).releaseSplashActivityComponent()
        super.onDestroy()
    }


    override fun startSunActivity() {
    }
}
