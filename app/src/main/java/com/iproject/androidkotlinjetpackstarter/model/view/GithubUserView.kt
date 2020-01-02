package com.iproject.androidkotlinjetpackstarter.model.view

import com.iproject.androidkotlinjetpackstarter.model.remote.GithubUserResponse

data class GithubUserView(
    var login: String? = "",
    var id: String? = "",
    var avatarUrl: String? = ""
) {
    fun fromGithubUserResponse(githubUserResponse: GithubUserResponse) {
        login = githubUserResponse.login
        id = githubUserResponse.id.toString()
        avatarUrl = githubUserResponse.avatar_url
    }
}