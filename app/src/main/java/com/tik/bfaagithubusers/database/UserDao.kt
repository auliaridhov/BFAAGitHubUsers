package com.tik.bfaagithubusers.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: User)
    @Update
    fun update(note: User)
    @Delete
    fun delete(note: User)
    @Query("SELECT * from user ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<User>>
    @Query("SELECT * FROM user WHERE login = :userName")
    fun getUserByUserName(userName: String): LiveData<User>
}