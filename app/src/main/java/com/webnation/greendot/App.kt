package com.webnation.greendot

import android.app.Application
import com.webnation.greendot.database.FibDatabase
import com.webnation.greendot.database.FibRepository
import com.webnation.greendot.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { MainViewModel(get(), get()) }

    single {
        getRepository(androidApplication())
    }

}

fun getRepository(androidApplication: Application): FibRepository {
    val fibDAO = FibDatabase.getDatabase(androidApplication).fibDao()
    return FibRepository(fibDAO)
}