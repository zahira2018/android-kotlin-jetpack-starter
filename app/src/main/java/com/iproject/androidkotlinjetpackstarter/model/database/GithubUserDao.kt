package com.iproject.androidkotlinjetpackstarter.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GithubUserDao {
    @Insert
    suspend fun insertAll(vararg users: GithubUser): List<Long>

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<GithubUser>

    @Query("SELECT * FROM users WHERE uuid = :userId")
    suspend fun getUser(userId: Int): GithubUser

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}