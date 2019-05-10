package com.test.testapplication.base

import android.content.Context
import android.util.Log


/**
 * @author Ruslan
 * Date: May, 11, 2019
 * Time: 00:58
 */

@Suppress("UNCHECKED_CAST")
abstract class BaseActivityPresenter<V : BaseActivityContract.BaseViews>(context: Context) : BaseActivityContract.BaseActions {
    protected val TAG = this.javaClass.simpleName

    protected var mAppContext: Context = context
    protected lateinit var mView: V

    override fun <View : BaseActivityContract.BaseViews> onViewAttached(view: View) {
        Log.d(TAG, "onViewAttached ----> Was Attached")
        mView = view as V
    }

    override fun onViewDetached() {
        Log.d(TAG, "onViewDetached -----> view was detached")
    }

    override fun onViewDestroyed() {
        Log.d(TAG, "onViewDestroyed ----> view was destroyed")
        release()
    }
}