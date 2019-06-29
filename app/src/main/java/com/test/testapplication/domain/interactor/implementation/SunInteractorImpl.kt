package com.test.testapplication.domain.interactor.implementation

import android.content.Context
import android.net.Uri
import com.test.testapplication.data.action.Next
import com.test.testapplication.data.usecase.SunAPIUC
import com.test.testapplication.domain.interactor.SunInteractor
import javax.inject.Inject


/**
 * @author Ruslan Stosyk
 * Date: May, 11, 2019
 * Time: 11:26
 */

class SunInteractorImpl @Inject
internal constructor() : BaseInteractorImpl(), SunInteractor {
    @Inject
    lateinit var appContext: Context

    @Inject
    lateinit var sunAPIUC: SunAPIUC


    override fun getSunInfo(data: Next<Any>, error: Error, uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}