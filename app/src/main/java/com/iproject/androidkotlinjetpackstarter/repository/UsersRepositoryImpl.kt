package com.iproject.androidkotlinjetpackstarter.repository

import com.iproject.androidkotlinjetpackstarter.api.UsersCoApi
import com.iproject.androidkotlinjetpackstarter.model.remote.GithubUserResponse
import retrofit2.Response

class UsersRepositoryImpl(private val usersCoApi: UsersCoApi) : UsersRepository {
    override suspend fun getUsersAsync(): Response<List<GithubUserResponse>> = usersCoApi.getUsersAsync()
}