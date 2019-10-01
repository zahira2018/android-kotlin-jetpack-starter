package com.iproject.androidkotlinjetpackstarter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iproject.androidkotlinjetpackstarter.model.database.GithubUser

class UserViewModel : ViewModel() {

    val githubUserList = MutableLiveData<List<GithubUser>>()
    val userListLoading = MutableLiveData<Boolean>()
    val userListError = MutableLiveData<Boolean>()

    fun refresh() {
        
    }
}