package com.zhaolongzhong.tiktok.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow

class Navigation {

    val screenState = MutableStateFlow(Screen.List)

    fun navigate(screen: Screen) {
        screenState.value = screen
    }
}

enum class Screen(
    val id: String,
) {
    List("list"),
    Detail("detail")
}
