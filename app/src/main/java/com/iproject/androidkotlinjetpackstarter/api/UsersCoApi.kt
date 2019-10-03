package com.iproject.androidkotlinjetpackstarter.api

import com.iproject.androidkotlinjetpackstarter.model.remote.GithubUserResponse
import retrofit2.Response
import retrofit2.http.GET

interface UsersCoApi {
    @GET("users")
    suspend fun getUsersAsync(): Response<List<GithubUserResponse>>
}