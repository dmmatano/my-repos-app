package com.example.myapprepositories.domain

import com.example.myapprepositories.core.UseCase
import com.example.myapprepositories.data.model.Repo
import com.example.myapprepositories.data.repositories.RepoRepository
import kotlinx.coroutines.flow.Flow

class ListUserRepositoriesUseCase(private val repository: RepoRepository):
    UseCase<String, List<Repo>>() {

    override suspend fun execute(param: String): Flow<List<Repo>> {
        return repository.listRepositories(param)
    }
}