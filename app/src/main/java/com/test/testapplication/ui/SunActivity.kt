package com.test.testapplication.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.test.testapplication.App
import com.test.testapplication.R
import com.test.testapplication.base.BaseActivity
import com.test.testapplication.ui.SunActivityContract.*
import com.test.testapplication.utills.GlideUtils
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

class SunActivity : BaseActivity<Actions>(), Views {


    companion object {
        const val PERMISSION_READ_WRITE = 1025
        const val PICKER_REQUEST_CODE = 1027
    }

    private lateinit var mPreviewIv: ImageView
    private lateinit var mSelectImageBtn: Button
    private lateinit var mUploadFileBtn: Button

    private var mBufferedPhotoUri: Uri? = null
    private var mPhotoUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        //(application as App).getSunActivityComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sun)
        initView()
    }

    private fun initView() {
       /* mPreviewIv = findViewById(R.id.upload_file_activity_preview_iv)
        mSelectImageBtn = findViewById(R.id.upload_file_activity_select_image_btn)
        mSelectImageBtn.setOnClickListener { actions.checkPermissionReadWriteStorage() }

        mUploadFileBtn = findViewById(R.id.upload_file_activity_upload_image_btn)
        mUploadFileBtn.setOnClickListener {
            val uri = mPhotoUri
            if (uri != null) {
                actions.uploadAvatar(uri)
            }
        }*/

        loadImage()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun askPermissionReadWriteStorage() {
        Log.d(TAG, "askPermissionReadWriteStorage ----> make ask permissions")
        requestPermissions(
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ), PERMISSION_READ_WRITE
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d(TAG, "onRequestPermissionsResult ----> requestCode = $requestCode, permissions = $permissions")
        if (grantResults.any { it != PackageManager.PERMISSION_GRANTED }) {
            Log.d(TAG, "onRequestPermissionsResult ----> denied")
            Toast.makeText(applicationContext, R.string.upload_file_permission_denied, Toast.LENGTH_SHORT).show()
        } else {
            Log.d(TAG, "onRequestPermissionsResult ----> PERMISSION_CAMERA: success")
            when (requestCode) {
                PERMISSION_READ_WRITE -> actions.createFilePickerIntent()
            }
        }
    }

    override fun loadPikerIntent(pickPhotoIntent: Intent) {
        Log.d(TAG, "loadPikerIntent ----> startActivityForResult")
        startActivityForResult(pickPhotoIntent, PICKER_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        Log.d(
            TAG,
            "onActivityResult ----> requestCode = $requestCode, isResultCodeOk: ${resultCode == Activity.RESULT_OK}"
        )
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PICKER_REQUEST_CODE -> {
                    if (intentData != null && intentData.data is Uri) {
                        mBufferedPhotoUri = intentData.data as Uri
                        Log.d(TAG, "onActivityResult ----> PICKER_REQUEST_CODE: success")
                        loadCropper()
                    }
                    return
                }
                CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {
                    Log.d(TAG, "onActivityResult ----> CROP_IMAGE_ACTIVITY_REQUEST_CODE: success")
                    mPhotoUri = CropImage.getActivityResult(intentData).uri
                    loadImage()
                }
            }
        }
    }

    private fun loadCropper() {
        Log.d(TAG, "loadCropper ----> Start CropImageActivity path= ${mBufferedPhotoUri?.path}")
        val bufferedPhotoUri = mBufferedPhotoUri
        if (bufferedPhotoUri != null) {
            CropImage.activity(bufferedPhotoUri)
                .setAllowRotation(true)
                .setAutoZoomEnabled(true)
                .setAspectRatio(1, 1)
                .setFixAspectRatio(true)
                .setActivityTitle("Crop photo")
                .setGuidelines(CropImageView.Guidelines.ON)
                .setScaleType(CropImageView.ScaleType.FIT_CENTER)
                .start(this)
        }
    }

    private fun loadImage() {
        Log.d(TAG, "loadImage ----> path= ${mPhotoUri?.path}")
        GlideUtils.loadImage(applicationContext, mPhotoUri?.path, R.drawable.place, mPreviewIv)
        disableFinishBtn(mPhotoUri != null)
    }

    private fun disableFinishBtn(isClickable: Boolean) {
        mUploadFileBtn.background = applicationContext?.getDrawable(
            if (isClickable) R.drawable.bg_blue_button
            else R.drawable.bg_gray_button
        )
        mUploadFileBtn.isClickable = isClickable

    }
}
