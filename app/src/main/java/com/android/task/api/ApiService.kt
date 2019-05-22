package com.android.task.api

import com.android.task.model.User
import retrofit2.Call
import retrofit2.http.GET

/**
 * REST API access points
 */
interface ApiService {

    @GET("/users")
    fun loadUsers(): Call<List<User>>
}
