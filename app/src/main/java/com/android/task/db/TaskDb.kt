package com.android.task.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.task.vo.User

/**
 * Main database description.
 */
@Database(
    entities = [
        User::class],
    version = 1,
    exportSchema = false
)
abstract class TaskDb : RoomDatabase() {

    abstract fun userDao(): UserDao
}
