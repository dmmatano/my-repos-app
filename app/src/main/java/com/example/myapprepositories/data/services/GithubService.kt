package com.example.myapprepositories.data.services

import com.example.myapprepositories.data.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{user}/repos")
    suspend fun  listRepositories(@Path("user") user: String?): List<Repo>

}