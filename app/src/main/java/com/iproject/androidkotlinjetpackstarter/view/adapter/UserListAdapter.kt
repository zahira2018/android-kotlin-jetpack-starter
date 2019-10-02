package com.iproject.androidkotlinjetpackstarter.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.iproject.androidkotlinjetpackstarter.R
import com.iproject.androidkotlinjetpackstarter.model.database.GithubUser
import com.iproject.androidkotlinjetpackstarter.model.view.GithubUserView
import com.iproject.androidkotlinjetpackstarter.util.getProgressDrawable
import com.iproject.androidkotlinjetpackstarter.util.loadImage
import com.iproject.androidkotlinjetpackstarter.view.ListFragmentDirections
import kotlinx.android.synthetic.main.user_list.view.*

class UserListAdapter(val userList: ArrayList<GithubUserView>) :
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    fun updateUserList(newUserList: List<GithubUserView>) {
        userList.clear()
        userList.addAll(newUserList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindItem(userList[position])
    }

    class UserViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private val list: ConstraintLayout = view.user_root_constraint_list

        init {
            list.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(ListFragmentDirections.actionDetailFragment())
            }
        }

        fun bindItem(githubUserView: GithubUserView) {
            view.user_name_text_list.text = githubUserView.login
            view.user_image_list.loadImage(githubUserView.avatarUrl, getProgressDrawable(view.context))
        }


    }
}