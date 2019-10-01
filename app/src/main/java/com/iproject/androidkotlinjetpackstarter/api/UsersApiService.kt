package com.iproject.androidkotlinjetpackstarter.api

import com.iproject.androidkotlinjetpackstarter.model.remote.GithubUserResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class UsersApiService {

    private val BASE_URL = "https://api.github.com/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(UsersApi::class.java)

    fun getUsers(): Single<List<GithubUserResponse>> {
        return api.getUsers()
    }
}