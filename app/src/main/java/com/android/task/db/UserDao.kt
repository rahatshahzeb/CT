package com.android.task.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.task.vo.User
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Interface for database access for User related operations.
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM user WHERE account = :account AND password = :password")
    fun getUser(account: String?, password: String?): Single<User>
}
