package com.iproject.androidkotlinjetpackstarter.repository

import com.iproject.androidkotlinjetpackstarter.model.remote.GithubUserResponse
import retrofit2.Response

interface UsersRepository {
    suspend fun getUsersAsync(): Response<List<GithubUserResponse>>
}