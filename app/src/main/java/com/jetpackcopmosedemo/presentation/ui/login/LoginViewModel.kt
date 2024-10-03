package com.jetpackcopmosedemo.presentation.ui.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jetpackcopmosedemo.data.UiState
import com.jetpackcopmosedemo.domain.repository.auth.AuthRepository
import com.jetpackcopmosedemo.service.NotificationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.mockwebserver.MockWebServer
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    var emailText = MutableStateFlow<String?>(null)
    var isEmailTextError = MutableStateFlow<Boolean?>(null)
    var passwordText = MutableStateFlow<String?>(null)
    var isPasswordTextError = MutableStateFlow<Boolean?>(null)
    private val _uiStateSignIn: MutableStateFlow<Any?> =
        MutableStateFlow(null)
    val uiStateSignIn: MutableStateFlow<Any?> = _uiStateSignIn
    private val mockWebServer = MockWebServer()

    internal fun signIn(context: Context) {
        _uiStateSignIn.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000)
            try {
                // Execute the request
                val response = authRepository.signIn(
                    mapOf(
                        "email" to (emailText.value ?: ""),
                        "password" to (passwordText.value ?: "")
                    )
                )

                if (response.code() == 200) {
                    _uiStateSignIn.value = UiState.Success("")
                    scheduleReminder(context = context)
                } else {
                    val type = object : TypeToken<Map<String, String>>() {}.type
                    val errorMessage = response.errorBody()
                    val errorResponse =
                        Gson().fromJson<Map<String, String>>(errorMessage?.string(), type)
                    Log.e("TAG", "startMockServer:--------------- $errorResponse")
                    _uiStateSignIn.value = UiState.Error(errorResponse["message"] ?: "")
                }
            } catch (e: Exception) {
                _uiStateSignIn.value = UiState.Error(e.message ?: "")
            }
        }
    }

    private fun scheduleReminder(
        duration: Long = 1,
        unit: TimeUnit = TimeUnit.MINUTES,
        context: Context
    ) {
        // create a Data instance with the plantName passed to it
        val workManager = WorkManager.getInstance(context)
        val myWorkRequestBuilder = OneTimeWorkRequestBuilder<ReminderWorker>()
        myWorkRequestBuilder.setInputData(
            workDataOf()
        )
        myWorkRequestBuilder.setInitialDelay(duration, unit)
        workManager.enqueue(myWorkRequestBuilder.build())
    }

    override fun onCleared() {
        super.onCleared()
        mockWebServer.shutdown()
    }
}

class ReminderWorker(
    private val context: Context,
    workerParams: WorkerParameters,
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val waterNotificationService = NotificationService(context)
        waterNotificationService.showBasicNotification()

        return Result.success()
    }
}