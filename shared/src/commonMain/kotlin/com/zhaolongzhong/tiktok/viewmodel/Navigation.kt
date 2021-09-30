package com.zhaolongzhong.tiktok.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow

class Navigation(private val stateManager: StateManager) {

    val screenState = MutableStateFlow(ScreenIdentifier(Screen.List))

    fun navigate(screenIdentifier: ScreenIdentifier) {
        // TODO: handle better for initializing destination screen
        if (screenIdentifier.screen == Screen.Detail) {
            screenIdentifier.params?.let { stateManager.getCountryInfo(it) }
        }
        screenState.value = screenIdentifier
    }
}

enum class Screen(
    val id: String,
) {
    List("list"),
    Detail("detail")
}


class ScreenIdentifier(
    val screen: Screen,
    var params: String? = null
)
