package com.jetpackcopmosedemo.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackcopmosedemo.domain.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    var emailText = MutableStateFlow<String?>(null)
    var isEmailTextError = MutableStateFlow<Boolean?>(null)
    var passwordText = MutableStateFlow<String?>(null)
    var isPasswordTextError = MutableStateFlow<Boolean?>(null)

    private val mockWebServer = MockWebServer()

    internal fun startMockServer(onSuccess: () -> Unit, onError: (String?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Execute the request
                val response: Response = authRepository.signIn(
                    mapOf(
                        "email" to emailText.value,
                        "password" to passwordText.value
                    ).toString()
                        .toRequestBody("application/x-www-form-urlencoded".toMediaType())
                )
                if (response.code == 200) {
                    onSuccess.invoke()
                } else {
                    val errorMessage = response.body?.string()
                    onError.invoke(errorMessage)
                }
            } catch (e: Exception) {
                onError.invoke("Error: ${e.message}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        // Shutdown the MockWebServer when ViewModel is cleared
        mockWebServer.shutdown()
    }
}
