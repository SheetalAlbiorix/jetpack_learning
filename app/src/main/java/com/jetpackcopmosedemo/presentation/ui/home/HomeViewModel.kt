package com.jetpackcopmosedemo.presentation.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jetpackcopmosedemo.data.UiState
import com.jetpackcopmosedemo.domain.repository.event.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository,
) : ViewModel() {

    private val _uiStateMockEvents: MutableStateFlow<UiState<MutableList<String>>> =
        MutableStateFlow(UiState.Loading)
    val uiStateMockEvents: StateFlow<UiState<MutableList<String>>> = _uiStateMockEvents
    private val mockWebServer = MockWebServer()
    private val gson = Gson()

    init {
        getEvents()
    }

    private fun getEvents() {
        _uiStateMockEvents.value = UiState.Loading
        viewModelScope.launch {
            delay(1000)
            try {
                // Execute the request
                val response = dashboardRepository.getEvent()
                if (response.code() == 200) {
                    response.body()?.get("events")?.let {
                        _uiStateMockEvents.value = UiState.Success(it.toMutableList())
                    }
                } else {
                    val type = object : TypeToken<Map<String, String>>() {}.type
                    val errorMessage = response.errorBody()
                    val errorResponse =
                        gson.fromJson<Map<String, String>>(errorMessage?.string(), type)
                    Log.e("TAG", "startMockServer:--------------- $errorResponse")
                    _uiStateMockEvents.value = UiState.Error(errorResponse["message"] ?: "")
                }
            } catch (e: Exception) {
                _uiStateMockEvents.value = UiState.Error(e.message ?: "")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        mockWebServer.shutdown()
    }
}