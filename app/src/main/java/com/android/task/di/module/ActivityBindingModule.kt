package com.android.task.di.module

import com.android.task.ui.main.MainActivity
import dagger.android.ContributesAndroidInjector
import dagger.Module

@Module
@Suppress("unused")
abstract class ActivityBindingModule {

//    @ContributesAndroidInjector(modules = [MainActivityFragmentModule::class])
//    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}