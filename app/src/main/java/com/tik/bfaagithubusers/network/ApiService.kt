package com.tik.bfaagithubusers.network

import com.tik.bfaagithubusers.model.DetailUser
import com.tik.bfaagithubusers.model.Items
import com.tik.bfaagithubusers.model.ResponseSearch
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun searchUsers(
        @Query("q") q: String
    ): Call<ResponseSearch>


    @GET("users/{username}")
    fun getDetailUsers(
        @Path("username") username: String
    ): Call<DetailUser>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<Items>>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<Items>>

}