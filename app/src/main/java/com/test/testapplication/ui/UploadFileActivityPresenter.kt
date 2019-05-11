package com.test.testapplication.ui

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.support.v4.content.ContextCompat
import android.util.Log
import com.test.testapplication.base.BaseActivityPresenter
import com.test.testapplication.ui.UploadFileActivityContract.*
import javax.inject.Inject


/**
 * @author Ruslan Stosyk
 * Date: May, 11, 2019
 * Time: 9:37
 */

class UploadFileActivityPresenter @Inject
internal constructor(appContext: Context): BaseActivityPresenter<Views>(appContext), Action{

    override fun checkPermissionReadWriteStorage() {
        Log.d(TAG, "checkPermissionReadWriteStorage ----> check permission")
        if (isViewAttached()) {
            if (arrayListOf(ContextCompat.checkSelfPermission(mAppContext, Manifest.permission.READ_EXTERNAL_STORAGE),
                            ContextCompat.checkSelfPermission(mAppContext, Manifest.permission.WRITE_EXTERNAL_STORAGE))
                            .any { it != PackageManager.PERMISSION_GRANTED }) {
                Log.d(TAG, "checkPermissionReadWriteStorage ----> permissions not accepted")
                mView?.askPermissionReadWriteStorage()
            } else {
                Log.d(TAG, "checkPermissionReadWriteStorage ----> permissions accepted")
                createFilePickerIntent()
            }
        }
    }

    override fun createFilePickerIntent() {
        Log.d(TAG, "createFilePickerIntent ----> startCreatingGalleryIntent")
        val pickPhotoIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickPhotoIntent.type = "image/*"
        if (isViewAttached()) {
            mView?.loadPikerIntent(pickPhotoIntent)
        }
    }

    override fun uploadAvatar(photoUri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
