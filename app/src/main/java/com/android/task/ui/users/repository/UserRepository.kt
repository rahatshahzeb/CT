package com.android.task.ui.users.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.task.api.ApiService
import com.android.task.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun loadUsers(): LiveData<List<User>> {
        var userList  =MutableLiveData<List<User>>()
        apiService.loadUsers().enqueue(object: Callback<List<User>> {

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                Log.d("Users list:", response.body().toString())
                userList.value = response.body()
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.e("Error:", t.message.toString())
            }
        })
        return userList
    }
}