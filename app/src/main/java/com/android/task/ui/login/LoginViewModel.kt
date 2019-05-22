package com.android.task.ui.login

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.task.ui.login.repository.LoginRepository
import com.android.task.util.SingleLiveEvent
import com.android.task.vo.User
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel() {

    val accountValue = MutableLiveData<String>()
    val passwordValue = MutableLiveData<String>()
    val isLoginError = MutableLiveData<Boolean>()
    val userData = MediatorLiveData<User>()
    val navHome = SingleLiveEvent<Any>()

    private fun isValidData(): Boolean {
        if (accountValue.value.isNullOrEmpty() || passwordValue.value.isNullOrEmpty()) {
            return false
        }
        return true
    }

    fun validateData() {
        if (isValidData()) {
            getUserFromDB()
        } else {
            isLoginError.value = true
        }
    }

    fun login(user: User) {
        val account: String? = accountValue.value
        val password: String? = passwordValue.value

        isLoginError.value = !(account.equals(user.account) && password.equals(user.password))

        if (isLoginError.value == false) {
            navHome.call()
        }
    }

    fun prePopulateUser() {
        loginRepository.insertUser(User("SHAHZEB", "123456", "MALAYSIA"))
    }

    private fun getUserFromDB() {
        userData.addSource(loginRepository.getUser(accountValue.value, passwordValue.value)) {
                value -> userData.value = value
        }
    }

    fun handleLoginResult(user: User?) {
        if (user == null) {
            isLoginError.value = true
        } else {
            isLoginError.value = false
            login(user)
        }
    }
}