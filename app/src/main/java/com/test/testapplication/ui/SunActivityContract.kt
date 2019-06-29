package com.test.testapplication.ui

import android.content.Intent
import android.net.Uri
import com.test.testapplication.base.BaseActivityContract


/**
 * @author Ruslan Stosyk
 * Date: May, 11, 2019
 * Time: 9:35
 */

class SunActivityContract : BaseActivityContract() {
    interface Actions : BaseActions {

        fun checkPermissionReadWriteStorage()

        fun createFilePickerIntent()

        fun uploadAvatar(photoUri: Uri)
    }

    interface Views : BaseViews {
        fun askPermissionReadWriteStorage()

        fun loadPikerIntent(pickPhotoIntent: Intent)

    }
}