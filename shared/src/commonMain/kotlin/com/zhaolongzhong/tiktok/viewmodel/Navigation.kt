package com.zhaolongzhong.tiktok.viewmodel

import com.zhaolongzhong.tiktok.viewmodel.screens.country_detail.CountryDetailState
import com.zhaolongzhong.tiktok.viewmodel.screens.country_detail.initCountryDetailInit
import com.zhaolongzhong.tiktok.viewmodel.screens.country_list.initCountriesList
import kotlinx.coroutines.flow.MutableStateFlow

class Navigation(private val stateManager: StateManager) {

    val screenState = MutableStateFlow(ScreenIdentifier(Screen.List))

    fun navigate(screenIdentifier: ScreenIdentifier) {
        // TODO: handle better for initializing destination screen
        if (screenIdentifier.screen == Screen.Detail) {
            screenIdentifier.params?.let { stateManager.getCountryInfo(it) }
            val screenInitSettings = screenIdentifier.screen.initSettings(this, screenIdentifier)
            val screenState = screenInitSettings.initState(screenIdentifier)
            stateManager.setDetailState(screenState as CountryDetailState)
        }
        screenState.value = screenIdentifier
    }

    fun onReEnterForeground() {
        debugLogger.log("onReEnterForeground: recomposition is triggered")
        stateManager.triggerRecomposition()
    }

    fun onEnterBackground() {
        debugLogger.log("onEnterBackground: screen scopes are cancelled")
        stateManager.cancelScreenScopes()
    }

    fun onChangeOrientation() {
        debugLogger.log("onChangeOrientation: recomposition is triggered")
        stateManager.triggerRecomposition()
    }
}

enum class Screen(
    val id: String,
    val initSettings: Navigation.(ScreenIdentifier) -> ScreenInitSettings
) {
    List("list", { initCountriesList() }),
    Detail("detail", { initCountryDetailInit() });
}


class ScreenIdentifier(
    val screen: Screen,
    var params: String? = null
)

class ScreenInitSettings(
    val title: String,
    val initState: (ScreenIdentifier) -> ScreenState
)

interface ScreenState