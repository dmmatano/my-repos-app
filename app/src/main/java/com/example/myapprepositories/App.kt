package com.example.myapprepositories

import android.app.Application
import com.example.myapprepositories.data.di.DataModule
import com.example.myapprepositories.domain.di.DomainModule
import com.example.myapprepositories.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()

    }

}