package com.zhaolongzhong.tiktok.viewmodel

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.zhaolongzhong.tiktok.datalayer.Repository
import mylocal.db.LocalDb

fun TViewModel.Factory.getAndroidInstance(context: Context): TViewModel {
    val sqlDriver = AndroidSqliteDriver(LocalDb.Schema, context, "Local.db")
    return TViewModel((Repository(sqlDriver)))
}
