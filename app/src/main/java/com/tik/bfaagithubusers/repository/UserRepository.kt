package com.tik.bfaagithubusers.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.tik.bfaagithubusers.database.User
import com.tik.bfaagithubusers.database.UserDao
import com.tik.bfaagithubusers.database.UserRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserRepository(application: Application) {
    private val mUsersDao: UserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = UserRoomDatabase.getDatabase(application)
        mUsersDao = db.userDao()
    }
    fun getAllNotes(): LiveData<List<User>> = mUsersDao.getAllNotes()

    fun getUserByUserName(userName: String): LiveData<User> = mUsersDao.getUserByUserName(userName)

    fun insert(user: User) {
        executorService.execute { mUsersDao.insert(user) }
    }
    fun delete(user: User) {
        executorService.execute { mUsersDao.delete(user) }
    }
    fun update(user: User) {
        executorService.execute { mUsersDao.update(user) }
    }
}