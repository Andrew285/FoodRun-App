package com.rainyday.foodrun.core.network

import com.rainyday.foodrun.core.datastore.TokenDataStore
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class AuthEventBus @Inject constructor(
    private val tokenDataStore: TokenDataStore
) {
    private val _events = MutableSharedFlow<AuthState>()
    val events: SharedFlow<AuthState> = _events.asSharedFlow()

    suspend fun emitUnauthorized() {
        _events.emit(AuthState.Unauthorized)
    }
}

sealed class AuthState {
    object Unauthorized: AuthState()
}