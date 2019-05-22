package com.android.task.ui.users

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.android.task.model.User
import com.android.task.ui.users.repository.UserRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(
    userRepository: UserRepository
): ViewModel() {

    val userList =  MediatorLiveData<List<User>>()

    init {
        userList.addSource(
            userRepository.loadUsers()
        ) { value -> userList.value = value}
    }
}