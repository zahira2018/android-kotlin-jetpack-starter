package com.iproject.androidkotlinjetpackstarter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iproject.androidkotlinjetpackstarter.model.database.GithubUser

class UserViewModel : ViewModel() {

    private val githubUserViewList = MutableLiveData<List<GithubUser>>()
    private val userListLoading = MutableLiveData<Boolean>()
    private val userListError = MutableLiveData<Boolean>()

    fun refresh() {
        val user1 = GithubUser(
            "Ronaldo",
            "1",
            "https://cdn.images.express.co.uk/img/dynamic/67/590x/Why-is-Cristiano-Ronaldo-not-playing-for-Juventus-vs-Brescia-after-Fifa-Best-awards-snub-1182201.jpg?r=1569356281389"
        )
        val user2 = GithubUser(
            "Messi",
            "2",
            "https://www.fcbarcelona.com/photo-resources/2019/05/13/cb6216c0-1086-4ca1-bcc3-9808deb61fd4/mini_Messi-celebraci-gol.jpg?width=1200&height=750"
        )
        val userList = listOf(user1, user2)

        githubUserViewList.value = userList
        userListLoading.value = false
        userListError.value = false
    }
}