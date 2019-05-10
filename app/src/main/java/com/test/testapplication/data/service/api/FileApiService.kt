package com.test.testapplication.data.service.api

import io.reactivex.Flowable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*


/**
 * @author Ruslan Stosyk
 * Date: May, 11, 2019
 * Time: 1:25
 */

interface FileApiService {

    companion object {
        const val FILE_UPLOAD = "/upload"
    }

    @Multipart
    @POST(FILE_UPLOAD)
    fun uploadPywareFile(
        @Field("checksum") type: String,
        @Part files: List<MultipartBody.Part>
    ): Flowable<Any>
}