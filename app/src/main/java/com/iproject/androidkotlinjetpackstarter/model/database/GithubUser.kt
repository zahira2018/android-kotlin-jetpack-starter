package com.iproject.androidkotlinjetpackstarter.model.database

import androidx.room.Entity

@Entity
data class GithubUser(
    var login: String? = "",
    var id: String? = "",
    var avatarUrl: String? = ""
)