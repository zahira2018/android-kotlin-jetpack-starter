package com.iproject.androidkotlinjetpackstarter.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.iproject.androidkotlinjetpackstarter.model.database.DatabaseHelper
import com.iproject.androidkotlinjetpackstarter.model.database.GithubUser
import com.iproject.androidkotlinjetpackstarter.model.remote.GithubUserResponse
import com.iproject.androidkotlinjetpackstarter.model.view.GithubUserView
import com.iproject.androidkotlinjetpackstarter.repository.UsersRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application, private val usersRepository: UsersRepository) :
    BaseViewModel(application) {

    val githubUserViewList = MutableLiveData<List<GithubUserView>>()
    val userListLoading = MutableLiveData<Boolean>()
    val userListError = MutableLiveData<Boolean>()

    fun refresh() {
        fetchRemoteUsingCoroutines()
    }

    private fun fetchRemoteUsingCoroutines() {
        launch {
            userListLoading.value = true
            val result = usersRepository.getUsersAsync()
            if (result.isSuccessful) {
                userListError.value = false
                userListLoading.value = false
                val list = result.body()?.map {


                }
                list?.let {

                }
            } else {
                userListError.value = true
                userListLoading.value = false
            }
        }
    }

    private fun storeUsersLocally(list: List<GithubUser>) {
        launch {
            val dao = DatabaseHelper(getApplication()).githubUserDao()
            dao.deleteAllUsers()
            val result = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].uuid = result[i].toInt()
                ++i
            }
            githubUserViewList.value = list.map {
                GithubUserView(
                    login = it.login,
                    id = it.id.toString(),
                    avatarUrl = it.avatarUrl
                )
            }
        }
    }
}