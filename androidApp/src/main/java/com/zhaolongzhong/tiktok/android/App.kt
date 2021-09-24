package com.zhaolongzhong.tiktok.android

import android.app.Application
import com.zhaolongzhong.tiktok.viewmodel.TViewModel
import com.zhaolongzhong.tiktok.viewmodel.getAndroidInstance

class TApp : Application() {

    lateinit var model: TViewModel

    override fun onCreate() {
        super.onCreate()
        model = TViewModel.Factory.getAndroidInstance(this)
    }
}
