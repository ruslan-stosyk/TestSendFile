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
import com.test.testapplication.ui.SunActivityContract.*
import javax.inject.Inject
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


/**
 * @author Ruslan Stosyk
 * Date: May, 11, 2019
 * Time: 9:37
 */

class SunActivityPresenter @Inject
internal constructor(appContext: Context) : BaseActivityPresenter<Views>(appContext), Actions {

    override fun checkPermissionReadWriteStorage() {
        Log.d(TAG, "checkPermissionReadWriteStorage ----> check permission")
        if (isViewAttached()) {
            if (arrayListOf(ContextCompat.checkSelfPermission(mAppContext, Manifest.permission.READ_EXTERNAL_STORAGE),
                    ContextCompat.checkSelfPermission(mAppContext, Manifest.permission.WRITE_EXTERNAL_STORAGE))
                    .any { it != PackageManager.PERMISSION_GRANTED }
            ) {
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
        Log.d(TAG, getMD5EncryptedString(photoUri.path!!))
    }

    private fun getMD5EncryptedString(encTarget: String): String {
        var mdEnc: MessageDigest? = null
        try {
            mdEnc = MessageDigest.getInstance("MD5")
        } catch (e: NoSuchAlgorithmException) {
            println("Exception while encrypting to md5")
            e.printStackTrace()
        }
        // Encryption algorithm
        if (mdEnc != null) {
            mdEnc.update(encTarget.toByteArray(), 0, encTarget.length)
            var md5 = BigInteger(1, mdEnc.digest()).toString(16)
            while (md5.length < 32) {
                md5 = "0$md5"
            }
            return md5
        }
        return ""
    }
}
