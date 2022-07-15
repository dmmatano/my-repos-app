package com.example.myapprepositories.data.repositories

import com.example.myapprepositories.core.RemoteException
import com.example.myapprepositories.data.model.Repo
import com.example.myapprepositories.data.services.GithubService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RepoRepositoryImpl(private val service: GithubService): RepoRepository {

    override suspend fun listRepositories(user: String) = flow {
        try{
            val repoList = service.listRepositories(user)
            emit(repoList)

        } catch (e: HttpException){
            throw RemoteException(e.message ?: "Não foi possível fazer a busca")
        }

    }
}