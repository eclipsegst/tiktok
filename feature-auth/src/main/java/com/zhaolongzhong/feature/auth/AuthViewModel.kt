package com.zhaolongzhong.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class AuthViewModel: ViewModel() {
    private val authStepStateFlow: MutableStateFlow<AuthScreenState> = MutableStateFlow(AuthScreenState.Loading)

    val authScreenStateUI: StateFlow<AuthScreenState> = authStepStateFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AuthScreenState.Loading
    )

    init {
        // TODO: check if it's authenticated state, e.g. by check if the access token
    }

    fun signIn(username: String, password: String) {
        authStepStateFlow.value = AuthScreenState.Home
    }

    fun signUp(username: String, password: String) {
        authStepStateFlow.value = AuthScreenState.Home
    }
}

sealed interface AuthScreenState {
    object Loading: AuthScreenState
    object Welcome: AuthScreenState
    object SignIn: AuthScreenState
    object SignUp: AuthScreenState
    object Home: AuthScreenState
}
