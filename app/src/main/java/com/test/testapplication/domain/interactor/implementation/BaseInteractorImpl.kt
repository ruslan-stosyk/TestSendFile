package com.test.testapplication.domain.interactor.implementation

import android.content.Context
import android.net.ConnectivityManager
import android.provider.Settings
import android.util.Log
import com.test.testapplication.R
import com.test.testapplication.data.action.Complete
import com.test.testapplication.data.action.Next
import com.test.testapplication.data.action.Subscribe
import com.test.testapplication.domain.interactor.BaseInteractor
import io.reactivex.Flowable
import io.reactivex.functions.Function
import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.*
import javax.inject.Inject


/**
 * @author Ruslan Stosyk
 * Date: May, 11, 2019
 * Time: 11:27
 */

abstract class BaseInteractorImpl : BaseInteractor {

    protected val TAG = this.javaClass.simpleName

    @Inject
    lateinit var mAppContext: Context
    private var mSubscriptions: MutableList<Subscription>? = null


    override fun release() {
        if (mSubscriptions != null) {
            for (subscription in mSubscriptions!!) {
                subscription.cancel()
            }
            mSubscriptions!!.clear()
        }
    }

    private fun addSubscriptions(subscription: Subscription) {
        if (mSubscriptions == null) {
            mSubscriptions = ArrayList()
        }
        mSubscriptions!!.add(subscription)
    }

    protected fun <T> createSubscriber(subscribe: Subscribe, next: Next<T>, error: Error): Subscriber<T> {
        return createSubscriber(subscribe, next, error, null)
    }

    internal fun <T> checkConnection(): Function<T, Publisher<T>> {
        return Function { t ->
            val errMsg: String
            if (isAirplaneModeOn(mAppContext)) {
                errMsg = mAppContext.resources.getString(R.string.error_airplane_mode)
                throw Throwable(errMsg)
            } else if (checkInternetConnection(mAppContext)) { // inverse
                errMsg = mAppContext.resources.getString(R.string.error_internet_connection)
                throw Throwable(errMsg)
            }
            Flowable.just<T>(t)
        }
    }

    private fun isAirplaneModeOn(context: Context): Boolean {
        return Settings.Global.getInt(context.contentResolver, "airplane_mode_on", 0) != 0
    }

    private fun checkInternetConnection(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }

    protected fun <T> createSubscriber(
            subscribe: Subscribe,
            next: Next<T>?,
            error: Error?,
            complete: Complete?
    ): Subscriber<T> {
        return object : Subscriber<T> {
            override fun onSubscribe(s: Subscription) {
                addSubscriptions(s)
                subscribe.onSubscribe(s)
            }

            override fun onNext(data: T) {
                next?.onNext(data)
            }

            override fun onError(t: Throwable) {
                Log.d(TAG, "throwable -> $t")
            }

            override fun onComplete() {
                complete?.onComplete()
            }
        }
    }
}
