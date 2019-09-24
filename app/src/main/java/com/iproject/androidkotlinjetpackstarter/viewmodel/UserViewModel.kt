package com.iproject.androidkotlinjetpackstarter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iproject.androidkotlinjetpackstarter.model.view.GithubUserView

class UserViewModel : ViewModel() {

    val githubUserViewList = MutableLiveData<List<GithubUserView>>()
}