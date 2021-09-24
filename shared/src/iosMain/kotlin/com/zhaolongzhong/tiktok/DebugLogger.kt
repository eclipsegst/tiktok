package com.zhaolongzhong.tiktok

import platform.Foundation.NSLog

actual class DebugLogger actual constructor(tagString: String) {
    actual val tag = tagString

    actual fun log(message: String) {
        NSLog ("$tag : $message")
    }
}
