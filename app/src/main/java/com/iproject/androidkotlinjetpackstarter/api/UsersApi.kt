package com.iproject.androidkotlinjetpackstarter.api

import com.iproject.androidkotlinjetpackstarter.model.remote.GithubUserResponse
import io.reactivex.Single
import retrofit2.http.GET

// Use this if using rxjava
interface UsersApi {
    @GET("users")
    fun getUsers(): Single<List<GithubUserResponse>>
}