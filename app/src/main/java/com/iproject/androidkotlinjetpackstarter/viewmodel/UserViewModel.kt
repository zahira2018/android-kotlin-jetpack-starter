package com.iproject.androidkotlinjetpackstarter.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.iproject.androidkotlinjetpackstarter.api.UsersApiService
import com.iproject.androidkotlinjetpackstarter.api.UsersCoApiService
import com.iproject.androidkotlinjetpackstarter.model.database.DatabaseHelper
import com.iproject.androidkotlinjetpackstarter.model.database.GithubUser
import com.iproject.androidkotlinjetpackstarter.model.view.GithubUserView
import com.iproject.androidkotlinjetpackstarter.repository.UsersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) :
    BaseViewModel(application) {

    private val userService = UsersApiService()
    private val userServiceCo = UsersCoApiService()
    private val disposable = CompositeDisposable()

    private lateinit var usersRepository: UsersRepository

//    val githubUserViewList = MutableLiveData<List<GithubUserView>>()
//    val userListLoading = MutableLiveData<Boolean>()
//    val userListError = MutableLiveData<Boolean>()

    private val _githubUserViewList by lazy { MutableLiveData<List<GithubUserView>>() }
    val githubUserViewList: LiveData<List<GithubUserView>> by lazy { _githubUserViewList }

    val userListLoading = MutableLiveData<Boolean>()
    val userListError = MutableLiveData<Boolean>()

//    private val _calendarNextEventLiveData by lazy { MutableLiveData<DataState<List<CalendarNextEventResponse>>>() }
//    val calendarNextEventLiveData: LiveData<DataState<List<CalendarNextEventResponse>>> by lazy { _calendarNextEventLiveData }

//    private val _familyMemberLiveData by lazy { MutableLiveData<DataState<List<CalendarOwnerResponse>>>() }
//    val familyLiveData: LiveData<DataState<List<CalendarOwnerResponse>>> by lazy { _familyMemberLiveData }
//
//    private val _calendarEventLiveData by lazy { MutableLiveData<Event<DataState<List<CalendarEventResponse>>>>() }
//    val eventsLiveData: LiveData<Event<DataState<List<CalendarEventResponse>>>> by lazy { _calendarEventLiveData }
//
//    var loadingState = MutableLiveData<Boolean>()
//
//    fun getFamilyMember() {
//        launch {
//            loadingState.postValue(true)
//            when (val result = calendarEventRepository.getFamilyMemberAsync().awaitAndGet()) {
//                is Result.Success -> {
//                    loadingState.postValue(false)
//                    result.body?.let {
//                        DataState.Success(it.data)
//                    }.run(_familyMemberLiveData::postValue)
//                }
//                is Result.Failure -> {
//                    loadingState.postValue(false)
//                    result.toFailureDataState<List<CalendarOwnerResponse>>()
//                        .run(_familyMemberLiveData::postValue)
//                }
//            }
//        }
//    }

    fun refresh() {
//        fetchFromRemote()
        fetchRemoteUsingCoroutines()
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

//    private fun usersRetrieved(list: List<GithubUserView>) {
//        githubUserViewList.value = list
//        userListError.value = false
//        userListLoading.value = false
//    }

    private fun fetchRemoteUsingCoroutines() {
        launch {
            userListLoading.value = false
            try {
                val result = userServiceCo.getUsers()
                if (result.isSuccessful) {
                    Log.d("IKMAL", "HAHA : " + Gson().toJson(result.body()))
                    //Do your thing
                } else {
                    //Handle unsuccessful response
                }
            } catch (e: Exception) {
                //Handle error
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
//            val usersView = list.map {
//                GithubUserView(
//                    login = it.login,
//                    id = it.id.toString(),
//                    avatarUrl = it.avatarUrl
//                )
//            }
//            usersRetrieved(usersView)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}