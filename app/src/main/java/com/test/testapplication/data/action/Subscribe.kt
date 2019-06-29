package com.test.testapplication.data.action

import org.reactivestreams.Subscription


/**
 * @author Ruslan Stosyk
 * Date: May, 11, 2019
 * Time: 11:29
 */

interface Subscribe {

    fun onSubscribe(s: Subscription)

}