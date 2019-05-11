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
    protected var mView: V? = null

    override fun <View : BaseActivityContract.BaseViews> onViewAttached(view: View) {
        mView = view as V
        Log.d(TAG, "onViewAttached ----> Was Attached")
    }

    override fun onViewDetached() {
        mView = null
        Log.d(TAG, "onViewDetached -----> view was detached")
    }

    override fun onViewDestroyed() {
        mView = null
        Log.d(TAG, "onViewDestroyed ----> view was destroyed")
    }

    override fun isViewAttached(): Boolean {
        return mView != null
    }
}