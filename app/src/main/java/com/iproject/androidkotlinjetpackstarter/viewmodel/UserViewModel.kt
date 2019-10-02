package com.iproject.androidkotlinjetpackstarter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iproject.androidkotlinjetpackstarter.api.UsersApiService
import com.iproject.androidkotlinjetpackstarter.model.database.GithubUser
import com.iproject.androidkotlinjetpackstarter.model.view.GithubUserView
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class UserViewModel : ViewModel() {

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
                        githubUserViewList.value = t
                        userListError.value = false
                        userListLoading.value = false
                    }

                    override fun onError(e: Throwable) {
                        userListError.value = true
                        userListLoading.value = false
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}