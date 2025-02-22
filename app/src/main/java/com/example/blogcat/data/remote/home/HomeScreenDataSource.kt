package com.example.blogcat.data.remote.home

import com.example.blogcat.core.Resource
import com.example.blogcat.data.model.Post
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class HomeScreenDataSource {
    suspend fun getLatestPosts(): Resource<List<Post>> {
        val postList = mutableListOf<Post>()
        val querySnapshot = FirebaseFirestore.getInstance().collection("posts").get().await()
        for (post in querySnapshot){
            post.toObject(Post::class.java)?.let {
                postList.add(it)
            }
        }
        return Resource.Success(postList)
    }
}