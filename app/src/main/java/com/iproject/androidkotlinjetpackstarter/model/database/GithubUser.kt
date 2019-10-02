package com.iproject.androidkotlinjetpackstarter.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class GithubUser(
    @ColumnInfo(name = "login")
    var login: String? = "",
    @ColumnInfo(name = "id")
    var id: String? = "",
    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String? = ""
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}