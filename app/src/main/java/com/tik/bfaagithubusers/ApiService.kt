package com.tik.bfaagithubusers

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

//    @FormUrlEncoded
//    @Headers("Authorization: token 12345")
//    @POST("review")
//    fun postReview(
//        @Field("id") id: String,
//        @Field("name") name: String,
//        @Field("review") review: String
//    ): Call<PostReviewResponse>
}