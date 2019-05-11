package com.test.testapplication.di.component

import com.test.testapplication.di.module.UploadFileActivityModule
import com.test.testapplication.scope.UploadFileScope
import com.test.testapplication.ui.UploadFileActivity
import dagger.Subcomponent


/**
 * @author Ruslan Stosyk
 * Date: May, 11, 2019
 * Time: 10:46
 */

@Subcomponent(modules = [UploadFileActivityModule::class])
@UploadFileScope
interface UploadFileActivityComponent {

    fun inject(activity: UploadFileActivity)

}