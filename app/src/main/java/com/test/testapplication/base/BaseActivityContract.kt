package com.test.testapplication.base


/**
 * @author Ruslan
 * Date: May, 11, 2019
 * Time: 00:57
 */

abstract class BaseActivityContract {

    interface BaseActions {
        fun <View : BaseViews> onViewAttached(view: View)

        fun onViewDetached()

        fun onViewDestroyed()

        fun release()
    }

    interface BaseViews {

        fun showLoading()

        fun hideLoading()
    }
}