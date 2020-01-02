package com.iproject.androidkotlinjetpackstarter.di

import com.iproject.androidkotlinjetpackstarter.api.UsersCoApi
import com.iproject.androidkotlinjetpackstarter.api.UsersCoApiService
import com.iproject.androidkotlinjetpackstarter.repository.UsersRepository
import com.iproject.androidkotlinjetpackstarter.repository.UsersRepositoryImpl
import com.iproject.androidkotlinjetpackstarter.viewmodel.UserViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Modules {
    private val repoModules = module {
        single<UsersRepository> { UsersRepositoryImpl(get()) }
    }

    private val viewModelModules = module {
        viewModel { UserViewModel(get(), get()) }
    }

    private val networkModules = module {
        single<UsersCoApi> {
            UsersCoApiService.create(
                UsersCoApi::class.java
            )
        }
    }

    fun getAll() = listOf(
        repoModules,
        viewModelModules,
        networkModules
    )
}