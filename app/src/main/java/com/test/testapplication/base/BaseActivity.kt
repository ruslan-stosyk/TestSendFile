package com.test.testapplication.base

import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.test.testapplication.R
import com.test.testapplication.base.BaseActivityContract.*
import kotlinx.android.synthetic.main.activity_base.*
import javax.inject.Inject


/**
 * @author Ruslan
 * Date: May, 11, 2019
 * Time: 00:56
 */

abstract class BaseActivity<T : BaseActions> : AppCompatActivity(), BaseViews {

    protected val TAG = this.javaClass.simpleName

    @Inject
    protected lateinit var actions: T

    private lateinit var contentLayout: ConstraintLayout
    private var mDisableTouches: Boolean = false

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        super.setContentView(R.layout.activity_base)
        contentLayout = layoutInflater.inflate(layoutResID, base_activity_container) as ConstraintLayout
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        actions.onViewAttached(this)
    }

    override fun onDetachedFromWindow() {
        actions.onViewDetached()
        super.onDetachedFromWindow()
    }

    override fun onDestroy() {
        actions.onViewDestroyed()
        super.onDestroy()
    }

    override fun showLoading() {
        Log.d(TAG, "showLoading ----> Show")
        if (window != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
        }
        mDisableTouches = true
        base_activity_progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        Log.d(TAG, "hideLoading ----> Hide")
        mDisableTouches = false
        base_activity_progress_bar.visibility = View.GONE
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return !mDisableTouches && super.dispatchTouchEvent(ev)
    }

    override fun showSnackBar(message: String) {
        val textColor = Color.WHITE
        val backgroundColor = Color.BLACK
        val snackbar = Snackbar.make(contentLayout, message, Snackbar.LENGTH_LONG)
        val snackBarView = snackbar.view
        snackBarView.setBackgroundColor(backgroundColor)
        val textView = snackBarView.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textView.setTextColor(textColor)
        textView.gravity = Gravity.CENTER
        snackbar.show()
    }
}