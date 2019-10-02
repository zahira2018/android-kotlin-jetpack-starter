package com.iproject.androidkotlinjetpackstarter.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.iproject.androidkotlinjetpackstarter.api.UsersApiService
import com.iproject.androidkotlinjetpackstarter.model.database.DatabaseHelper
import com.iproject.androidkotlinjetpackstarter.model.database.GithubUser
import com.iproject.androidkotlinjetpackstarter.model.view.GithubUserView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : BaseViewModel(application) {

    private val userService = UsersApiService()
    private val disposable = CompositeDisposable()

    val githubUserViewList = MutableLiveData<List<GithubUserView>>()
    val userListLoading = MutableLiveData<Boolean>()
    val userListError = MutableLiveData<Boolean>()

    fun refresh() {
        fetchFromRemote()
    }

    private fun fetchFromRemote() {
        userListLoading.value = true
        disposable.add(
            userService.getUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { responseList ->
                    responseList.map {
                        GithubUserView(
                            login = it.login,
                            id = it.id.toString(),
                            avatarUrl = it.avatar_url
                        )
                    }

                }
                .subscribeWith(object : DisposableSingleObserver<List<GithubUserView>>() {
                    override fun onSuccess(t: List<GithubUserView>) {
                        val githubUserList = t.map {
                            GithubUser(
                                login = it.login,
                                id = it.id,
                                avatarUrl = it.avatarUrl
                            )
                        }
                        storeUsersLocally(githubUserList)


                    }

                    override fun onError(e: Throwable) {
                        userListError.value = true
                        userListLoading.value = false
                    }

                })
        )
    }

    private fun usersRetrieved(list: List<GithubUserView>) {
        githubUserViewList.value = list
        userListError.value = false
        userListLoading.value = false
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
            val usersView = list.map {
                GithubUserView(
                    login = it.login,
                    id = it.id.toString(),
                    avatarUrl = it.avatarUrl
                )
            }
            usersRetrieved(usersView)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}