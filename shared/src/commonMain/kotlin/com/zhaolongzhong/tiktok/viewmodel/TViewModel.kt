package com.zhaolongzhong.tiktok.viewmodel

import com.zhaolongzhong.tiktok.DebugLogger
import com.zhaolongzhong.tiktok.datalayer.Repository
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
val debugLogger by lazy { DebugLogger("SAMPLE") }

class TViewModel(val repo: Repository) {
    companion object Factory {
        // factory methods are defined in the platform-specific shared code (androidMain and iosMain)
    }

    val stateManager by lazy { StateManager(repo) }
    val navigation by lazy { Navigation(stateManager) }
}
