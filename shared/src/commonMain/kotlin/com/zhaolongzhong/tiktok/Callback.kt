package com.zhaolongzhong.tiktok

sealed class Callback<out T> {
    data class Success<out R>(val value: R): Callback<R>()
    data class Failure(
        val message: String?,
        val throwable: Throwable? = null
    ): Callback<Nothing>()
}
