package com.jetpackcopmosedemo.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackcopmosedemo.data.network.client.getRetrofitInstance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _apiResponse = MutableStateFlow("")
    val apiResponse: StateFlow<String> = _apiResponse
    var emailText = MutableStateFlow<String?>(null)
    var isEmailTextError = MutableStateFlow<Boolean?>(null)
    var passwordText = MutableStateFlow<String?>(null)
    var isPasswordTextError = MutableStateFlow<Boolean?>(null)

    private val mockWebServer = MockWebServer()

    internal fun startMockServer(onSuccess: () -> Unit, onError: (String?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Make a request using OkHttp client
                val baseUrl = mockWebServer.url("https://example.com/api").toString()
                val request = Request.Builder().url("$baseUrl/login")
                    .post(
                        mapOf(
                            "email" to emailText.value,
                            "password" to passwordText.value
                        ).toString()
                            .toRequestBody("application/x-www-form-urlencoded".toMediaType())
                    ).build()

                // Execute the request
                val response: Response = getRetrofitInstance().newCall(request).execute()
                if (response.code == 200) {
                    onSuccess.invoke()
                } else {
                    val errorMessage = response.body?.string()
                    onError.invoke(errorMessage)
                }
                // Update the UI with the response
                _apiResponse.value = response.body?.string() ?: "Error: No response"
            } catch (e: Exception) {
                _apiResponse.value = "Error: ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        // Shutdown the MockWebServer when ViewModel is cleared
        mockWebServer.shutdown()
    }

}