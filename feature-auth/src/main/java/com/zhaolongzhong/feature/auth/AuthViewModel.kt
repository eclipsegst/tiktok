package com.zhaolongzhong.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class AuthViewModel: ViewModel() {
    private val authStepStateFlow: MutableStateFlow<AuthStepState> = MutableStateFlow(AuthStepState.Loading)

    val authStepUI: StateFlow<AuthStepState> = authStepStateFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AuthStepState.Loading
    )

    init {
        // Check if there's access in token local cache
        // if there's a access token -> go to home
        // if there's no access token -> go to welcome screen
    }

    fun openWelcome() {
        authStepStateFlow.value = AuthStepState.Welcome
    }

    fun openSignIn() {
        authStepStateFlow.value = AuthStepState.SignInScreen("", "")
    }

    fun openSignUp() {
        authStepStateFlow.value = AuthStepState.SignUpScreen("", "")
    }

    fun signIn(username: String, password: String) {
        // api call
        // callback, then change ui state
        authStepStateFlow.value = AuthStepState.Home
    }

    fun signUp(username: String, password: String) {
        //

        // error 402: message, username is exist
        // api call
        // callback, then change ui state

    }
}

sealed interface AuthStepState {
    object Loading: AuthStepState
    object Welcome: AuthStepState
    data class SignInScreen(val username: String, val password: String): AuthStepState
    data class SignUpScreen(val username: String, val password: String): AuthStepState
    object Home: AuthStepState
}

sealed interface AuthState {
    object Auth
    object Unauth
}
