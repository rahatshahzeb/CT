package com.android.task.vo

import androidx.room.Entity

@Entity(primaryKeys = ["username"])
data class User(
    val username: String,
    val password: String,
    val country: String
)
