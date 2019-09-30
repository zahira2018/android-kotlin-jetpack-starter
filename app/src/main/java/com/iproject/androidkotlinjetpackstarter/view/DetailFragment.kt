package com.iproject.androidkotlinjetpackstarter.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.iproject.androidkotlinjetpackstarter.R
import com.iproject.androidkotlinjetpackstarter.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private lateinit var viewModel: DetailViewModel
    private var userUUID = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        arguments?.let {
            userUUID = DetailFragmentArgs.fromBundle(it).userUUID
            viewModel.fetch(userUUID.toString())
        }

        observerViewModel()
    }

    private fun observerViewModel() {
        viewModel.userLiveData.observe(this, Observer { user ->
            user?.let {
                detail_name.text = it.login
            }

        })
    }
}
