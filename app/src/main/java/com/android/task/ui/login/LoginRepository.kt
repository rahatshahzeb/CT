package com.android.task.ui.login

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.task.db.TaskDb
import com.android.task.vo.User
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val taskDB: TaskDb
) {

    fun getUser(account: String?, password: String?): LiveData<User> {
        val user = MutableLiveData<User>()
        taskDB.userDao().getUser(account, password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                object : DisposableSingleObserver<User>() {
                    override fun onSuccess(t: User) {
                        user.value = t
                    }

                    override fun onError(e: Throwable) {
                        user.value = null
                    }
                })
        return user
    }

    @SuppressLint("CheckResult")
    fun insertUser(user: User) {
        Observable.just(taskDB)
            .subscribeOn(Schedulers.io())
            .subscribe { db -> db.userDao().insert(user) }
    }
}