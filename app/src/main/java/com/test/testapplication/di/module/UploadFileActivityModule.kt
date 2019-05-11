package com.test.testapplication.di.module

import com.test.testapplication.scope.UploadFileScope
import com.test.testapplication.ui.UploadFileActivityContract
import com.test.testapplication.ui.UploadFileActivityPresenter
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.NotNull


/**
 * @author Ruslan Stosyk
 * Date: May, 11, 2019
 * Time: 10:42
 */

@Module
public class UploadFileActivityModule {

    @Provides
    @NotNull
    @UploadFileScope
    fun provideUploadFileAction(uploadFileActivityPresenter: UploadFileActivityPresenter): UploadFileActivityContract.Action {
        return uploadFileActivityPresenter
    }
}