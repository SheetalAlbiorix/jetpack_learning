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

    private val _uiStateMockListEvents: MutableStateFlow<UiState<MutableList<String>>> =
        MutableStateFlow(UiState.Loading)
    val uiStateMockListEvents: StateFlow<UiState<MutableList<String>>> = _uiStateMockListEvents
    private val mockWebServer = MockWebServer()
    private val gson = Gson()

    init {
        getEvents()
    }

    private fun getEvents() {
        _uiStateMockListEvents.value = UiState.Loading
        viewModelScope.launch {
            delay(1000)
            try {
                // Execute the request
                val response = dashboardRepository.getEvent()
                if (response.code() == 200) {
                    response.body()?.get("events")?.let {
                        _uiStateMockListEvents.value = UiState.Success(it.toMutableList())
                    }
                } else {
                    val type = object : TypeToken<Map<String, String>>() {}.type
                    val errorMessage = response.errorBody()
                    val errorResponse =
                        gson.fromJson<Map<String, String>>(errorMessage?.string(), type)
                    Log.e("TAG", "startMockServer:--------------- $errorResponse")
                    _uiStateMockListEvents.value = UiState.Error(errorResponse["message"] ?: "")
                }
            } catch (e: Exception) {
                _uiStateMockListEvents.value = UiState.Error(e.message ?: "")
            }
        }
    }

    internal fun deleteEvents(eventIndex: Int) {
        _uiStateMockListEvents.value = UiState.Loading
        viewModelScope.launch {
            delay(1000)
            try {
                // Execute the request
                val response = dashboardRepository.deleteEvent(eventIndex)
                if (response.code() == 200) {
                    response.body()?.get("events")?.let {
                        _uiStateMockListEvents.value = UiState.Success(it.toMutableList())
                    }
                } else {
                    val type = object : TypeToken<Map<String, String>>() {}.type
                    val errorMessage = response.errorBody()
                    val errorResponse =
                        gson.fromJson<Map<String, String>>(errorMessage?.string(), type)
                    Log.e("TAG", "startMockServer:--------------- $errorResponse")
                    _uiStateMockListEvents.value = UiState.Error(errorResponse["message"] ?: "")
                }
            } catch (e: Exception) {
                _uiStateMockListEvents.value = UiState.Error(e.message ?: "")
                _uiStateMockListEvents.value = UiState.Loading
            }
        }
    }

    internal fun updateEvents(eventId: Int) {
        _uiStateMockListEvents.value = UiState.Loading
        viewModelScope.launch {
            delay(1000)
            try {
                // Execute the request
                val response = dashboardRepository.editEvent(eventId, mapOf("eventName" to "Khush"))
                if (response.code() == 200) {
                    response.body()?.get("events")?.let {
                        _uiStateMockListEvents.value = UiState.Success(it.toMutableList())
                    }
                } else {
                    val type = object : TypeToken<Map<String, String>>() {}.type
                    val errorMessage = response.errorBody()
                    val errorResponse =
                        gson.fromJson<Map<String, String>>(errorMessage?.string(), type)
                    Log.e("TAG", "startMockServer:--------------- $errorResponse")
                    _uiStateMockListEvents.value = UiState.Error(errorResponse["message"] ?: "")
                }
            } catch (e: Exception) {
                _uiStateMockListEvents.value = UiState.Error(e.message ?: "")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        mockWebServer.shutdown()
    }
}