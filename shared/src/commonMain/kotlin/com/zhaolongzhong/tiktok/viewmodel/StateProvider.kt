package com.zhaolongzhong.tiktok.viewmodel

class StateProvider (val stateManager : StateManager) {

    inline fun <reified T: ScreenState> get(screenIdentifier: ScreenIdentifier) : T {
        return when (screenIdentifier.screen) {
            Screen.List -> {
                stateManager.countryListScreenState as T
            }
            Screen.Detail -> {
                stateManager.detailState as T
            }
        }
    }

    // reified functions cannot be exported to iOS, so we use this function returning
    // the "ScreenState" interface type
    // on Swift, we then need to cast it to the specific state class
    fun getToCast(screenIdentifier: ScreenIdentifier) : ScreenState? {
        return when (screenIdentifier.screen) {
            Screen.List -> {
                stateManager.countryListScreenState.value
            }
            Screen.Detail -> {
                stateManager.detailState.value
            }
        }
    }
}
