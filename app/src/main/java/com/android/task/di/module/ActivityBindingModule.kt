package com.android.task.di.module

import com.android.task.ui.login.LoginActivity
import com.android.task.ui.users.UserDetailActivity
import com.android.task.ui.users.UserListActivity
import dagger.android.ContributesAndroidInjector
import dagger.Module

@Module
@Suppress("unused")
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun contributeUserListActivity(): UserListActivity

    @ContributesAndroidInjector(modules = [UserDetailActivityFragmentModule::class])
    abstract fun contributeUserDetailActivity(): UserDetailActivity
}