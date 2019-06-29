package com.test.testapplication.ui.splash

import com.test.testapplication.base.BaseActivityContract

/**
 * @author Ruslan Stosyk
 * Date: June, 29, 2019
 * Time: 15:06
 */

class SplashActivityContract : BaseActivityContract() {

    interface Actions : BaseActions {
        fun initCurrentLocation()
    }

    interface Views : BaseViews {
        fun startSunActivity()
    }
}
