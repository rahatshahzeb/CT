package com.android.task.vo

import androidx.room.Entity

@Entity(primaryKeys = ["account"])
data class User(
    val account: String,
    val password: String,
    val country: String
)
