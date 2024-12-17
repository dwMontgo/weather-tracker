package com.example.weathertracker.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertracker.domain.model.Weather
import com.example.weathertracker.domain.usecase.GetCurrentWeatherUseCase
import com.example.weathertracker.domain.usecase.GetSavedCityUseCase
import com.example.weathertracker.domain.usecase.SaveCityUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class HomeUiState(
    val isLoading: Boolean = false,
    val currentWeather: Weather? = null,
    val searchQuery: String = "",
    val searchResult: Weather? = null,
    val searchError: String? = null,
    val isCitySaved: Boolean = false
)

class HomeViewModel(
    private val getSavedCityUseCase: GetSavedCityUseCase,
    private val saveCityUseCase: SaveCityUseCase,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val apiKey: String
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    private val _searchQueryFlow = MutableStateFlow("")
    val searchQueryFlow = _searchQueryFlow.asStateFlow()

    init {
        viewModelScope.launch {
            getSavedCityUseCase().collectLatest { city ->
                if (city != null) {
                    fetchWeatherForCity(city)
                } else {
                    uiState = uiState.copy(isCitySaved = false, currentWeather = null)
                }
            }
        }

        viewModelScope.launch {
            searchQueryFlow
                .debounce(500)
                .filter { it.isNotBlank() }
                .collect { query ->
                    searchForCity(query)
                }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQueryFlow.value = query
        uiState = uiState.copy(searchQuery = query)
    }

    fun onCitySelected() {
        val weather = uiState.searchResult ?: return
        viewModelScope.launch {
            saveCityUseCase(weather.cityName)
        }
    }

    private fun fetchWeatherForCity(city: String) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, searchError = null)
            val result = getCurrentWeatherUseCase(city)
            uiState = if (result.isSuccess) {
                uiState.copy(
                    isLoading = false,
                    currentWeather = result.getOrNull(),
                    isCitySaved = true,
                    searchResult = null,
                    searchQuery = ""
                )
            } else {
                uiState.copy(
                    isLoading = false,
                    searchError = "Failed to load weather."
                )
            }
        }
    }

    private suspend fun searchForCity(query: String) {
        uiState = uiState.copy(isLoading = true, searchError = null, searchResult = null)
        val result = getCurrentWeatherUseCase(query)
        uiState = if (result.isSuccess) {
            uiState.copy(
                isLoading = false,
                searchResult = result.getOrNull(),
                searchError = null
            )
        } else {
            uiState.copy(
                isLoading = false,
                searchResult = null,
                searchError = "City not found"
            )
        }
    }
}
