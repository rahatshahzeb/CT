package com.android.task.di.module

import com.android.task.ui.users.UserDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("unused")
abstract class UserDetailActivityFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeUserDetailFragment(): UserDetailFragment
}