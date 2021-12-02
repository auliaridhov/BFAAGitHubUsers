package com.tik.bfaagithubusers.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tik.bfaagithubusers.database.User
import com.tik.bfaagithubusers.model.DetailUser
import com.tik.bfaagithubusers.network.ApiConfig
import com.tik.bfaagithubusers.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel (application: Application) : ViewModel()  {

    private val mUserRepository: UserRepository = UserRepository(application)

    private val _detailUser = MutableLiveData<DetailUser>()
    val detailUser: LiveData<DetailUser> = _detailUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "MainViewModel"
    }

    fun getDetailUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUsers(username)
        client.enqueue(object : Callback<DetailUser> {
            override fun onResponse(
                call: Call<DetailUser>,
                response: Response<DetailUser>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _detailUser.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUser>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun insert(user: User) {
        mUserRepository.insert(user)
    }

    fun getAllNotes(): LiveData<List<User>> = mUserRepository.getAllNotes()

    fun delete(user: User) {
        mUserRepository.delete(user)
    }

}