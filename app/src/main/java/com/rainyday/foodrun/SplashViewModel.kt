package com.rainyday.foodrun

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rainyday.foodrun.core.datastore.TokenDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenDataStore: TokenDataStore
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthCheckState>(AuthCheckState.Loading)
    val authState: StateFlow<AuthCheckState> = _authState.asStateFlow()

    init {
        checkAuth()
    }

    private fun checkAuth() {
        viewModelScope.launch {
            val token = tokenDataStore.token.firstOrNull()
            _authState.value = if (token != null) {
                AuthCheckState.Authenticated
            } else {
                AuthCheckState.Unauthenticated
            }
        }
    }
}

sealed class AuthCheckState {
    object Loading : AuthCheckState()
    object Authenticated : AuthCheckState()
    object Unauthenticated : AuthCheckState()
}