package com.test.testapplication.data.usecase

import android.content.Context
import com.test.testapplication.data.service.RestApiService
import javax.inject.Inject
import javax.inject.Singleton


/**
 * @author Ruslan Stosyk
 * Date: May, 11, 2019
 * Time: 11:43
 */

@Singleton
class SunAPIUC @Inject
internal constructor() : BaseUceCase() {

    @Inject
    lateinit var mContext: Context

    @Inject
    lateinit var mRestApiService: RestApiService

}