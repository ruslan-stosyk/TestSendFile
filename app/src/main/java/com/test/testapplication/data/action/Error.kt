package com.test.testapplication.data.action


/**
 * @author Ruslan Stosyk
 * Date: May, 11, 2019
 * Time: 1:33
 */

interface Error {
    fun onError(t: Throwable)
}