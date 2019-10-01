package com.iproject.androidkotlinjetpackstarter.api

import com.iproject.androidkotlinjetpackstarter.model.remote.GithubUserResponse
import io.reactivex.Single
import retrofit2.http.GET

interface UsersApi {
    @GET("users")
    fun getUsers(): Single<List<GithubUserResponse>>
}