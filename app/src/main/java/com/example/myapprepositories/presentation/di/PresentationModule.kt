package com.example.myapprepositories.presentation.di

import com.example.myapprepositories.data.di.DataModule
import com.example.myapprepositories.domain.ListUserRepositoriesUseCase
import com.example.myapprepositories.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load(){
        loadKoinModules(viewModuleModule())
    }

    private fun viewModuleModule(): Module {
        return module {
            viewModel{ MainViewModel(get())}

        }
    }
}