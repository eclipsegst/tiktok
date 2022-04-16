package com.zhaolongzhong.tiktok.viewmodel

import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.zhaolongzhong.tiktok.datalayer.Repository
import io.ktor.utils.io.core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import mylocal.db.LocalDb

fun TViewModel.Factory.getIOSInstance() : TViewModel {
    val sqlDriver = NativeSqliteDriver(LocalDb.Schema, "Local.db")
    val repository = Repository(sqlDriver)
    return TViewModel(repository)
}

fun TViewModel.getDefaultAppState() : AppState {
    return AppState()
}

fun TViewModel.onChange(provideNewState: ((AppState) -> Unit)) : Closeable {

    val job = Job()

    stateFlow.onEach {
        provideNewState(it)
    }.launchIn(
        CoroutineScope(Dispatchers.Main + job)
    )

    return object : Closeable {
        override fun close() {
            job.cancel()
        }
    }
}
