package com.test.testapplication.domain.interactor

import android.net.Uri
import com.test.testapplication.data.action.Next


/**
 * @author Ruslan Stosyk
 * Date: May, 11, 2019
 * Time: 11:25
 */

interface SunInteractor {

    fun getSunInfo(data: Next<Any>, error: Error, uri: Uri)
}