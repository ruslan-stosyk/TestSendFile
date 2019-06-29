package com.test.testapplication.data.service

import io.reactivex.Flowable
import okhttp3.MultipartBody
import retrofit2.http.Field
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


/**
 * @author Ruslan Stosyk
 * Date: May, 11, 2019
 * Time: 1:25
 */

interface RestApiService {



    @Multipart
    @POST("/dummy")
    fun dymmy(
        @Field("checksum") type: String,
        @Part files: List<MultipartBody.Part>
    ): Flowable<Any>
}