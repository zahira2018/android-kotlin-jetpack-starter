package com.iproject.androidkotlinjetpackstarter.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.iproject.androidkotlinjetpackstarter.R
import com.iproject.androidkotlinjetpackstarter.view.adapter.UserListAdapter
import com.iproject.androidkotlinjetpackstarter.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var viewModel: UserViewModel
    private val userListAdapter = UserListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        viewModel.refresh()

        list_recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userListAdapter
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.githubUserViewList.observe(this, Observer { users ->
            users?.let {
                list_recycler.visibility = View.VISIBLE
                userListAdapter.updateUserList(it)
            }

        })

        viewModel.userListError.observe(this, Observer { isError ->
            isError?.let {
                list_error.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.userListLoading.observe(this, Observer { isLoading ->
            isLoading?.let {
                if (it) {
                    list_progress_bar.visibility = View.VISIBLE
                    list_text_progress.visibility = View.VISIBLE
                    list_recycler.visibility = View.GONE
                    list_error.visibility = View.GONE
                } else {
                    list_progress_bar.visibility = View.GONE
                    list_text_progress.visibility = View.GONE
                }
            }
        })
    }
}
