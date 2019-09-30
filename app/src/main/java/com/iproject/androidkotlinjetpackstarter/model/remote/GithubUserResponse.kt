package com.iproject.androidkotlinjetpackstarter.model.remote

import com.squareup.moshi.Json

data class GithubUserResponse(
    @Json(name = "login")
    var login: String? = "",
    @Json(name = "id")
    var id: Int? = 0,
    @Json(name = "node_id")
    var node_id: String? = "",
    @Json(name = "avatar_url")
    var avatar_url: String? = "",
    @Json(name = "gravatar_id")
    var gravatar_id: String? = "",
    @Json(name = "url")
    var url: String? = "",
    @Json(name = "html_url")
    var html_url: String? = "",
    @Json(name = "followers_url")
    var followers_url: String? = "",
    @Json(name = "following_url")
    var following_url: String? = "",
    @Json(name = "gists_url")
    var gists_url: String? = "",
    @Json(name = "starred_url")
    var starred_url: String? = "",
    @Json(name = "subscriptions_url")
    var subscriptions_url: String? = "",
    @Json(name = "organizations_url")
    var organizations_url: String? = "",
    @Json(name = "repos_url")
    var repos_url: String? = "",
    @Json(name = "events_url")
    var events_url: String? = "",
    @Json(name = "received_events_url")
    var received_events_url: String? = "",
    @Json(name = "type")
    var type: String? = "",
    @Json(name = "site_admin")
    var site_admin: Boolean? = false
)

