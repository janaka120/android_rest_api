package com.example.retrofitapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapplication.databinding.PostItemBinding

class PostsAdapter(var postsList: ArrayList<Posts>): RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {
    inner class PostsViewHolder(val adapterBinding: PostItemBinding) : RecyclerView.ViewHolder(adapterBinding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PostsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.adapterBinding.textViewUserId.text = postsList[position].userId.toString()
        holder.adapterBinding.textViewId.text = postsList[position].id.toString()
        holder.adapterBinding.textViewTitle.text = postsList[position].title
        holder.adapterBinding.textViewBody.text = postsList[position].subtitle
    }
}