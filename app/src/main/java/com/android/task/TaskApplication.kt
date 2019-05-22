package com.android.task

import android.app.Activity
import android.app.Application
import com.android.task.di.AppInjector
import com.facebook.stetho.Stetho
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TaskApplication: Application(), HasActivityInjector {

    @Inject
    protected lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        Stetho.initializeWithDefaults(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}