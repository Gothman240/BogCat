package com.example.blogcat.domain.home

import com.example.blogcat.core.Resource
import com.example.blogcat.data.model.Post
import com.example.blogcat.data.remote.home.HomeScreenDataSource

class HomeScreenRepoImpl(private val dataSource: HomeScreenDataSource): HomeScreenRepo {
    override suspend fun getLatestPosts(): Resource<List<Post>> = dataSource.getLatestPosts()
}