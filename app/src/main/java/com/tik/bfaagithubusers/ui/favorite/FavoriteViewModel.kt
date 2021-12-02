package com.tik.bfaagithubusers.ui.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tik.bfaagithubusers.database.User
import com.tik.bfaagithubusers.repository.UserRepository

class FavoriteViewModel (application: Application) : ViewModel() {
    private val mUserRepository: UserRepository = UserRepository(application)
    fun getAllNotes(): LiveData<List<User>> = mUserRepository.getAllNotes()
}