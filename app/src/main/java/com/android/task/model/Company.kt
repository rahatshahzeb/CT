package com.android.task.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
): Parcelable