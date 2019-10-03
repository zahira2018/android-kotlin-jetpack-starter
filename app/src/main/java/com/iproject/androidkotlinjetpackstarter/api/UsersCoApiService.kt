package com.iproject.androidkotlinjetpackstarter.api

import com.iproject.androidkotlinjetpackstarter.model.remote.GithubUserResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class UsersCoApiService {

    private val BASE_URL = "https://api.github.com/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(UsersCoApi::class.java)

    suspend fun getUsers(): Response<List<GithubUserResponse>> {
        return api.getUsersAsync()
    }
}