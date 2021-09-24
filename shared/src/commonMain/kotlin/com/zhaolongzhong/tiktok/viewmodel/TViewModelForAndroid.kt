package com.zhaolongzhong.tiktok.viewmodel

import android.content.Context
import com.zhaolongzhong.tiktok.datalayer.Repository

fun TViewModel.Factory.getAndroidInstance(context: Context): TViewModel {
    return TViewModel((Repository()))
}
