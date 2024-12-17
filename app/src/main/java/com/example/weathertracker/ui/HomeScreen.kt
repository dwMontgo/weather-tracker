package com.example.weathertracker.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import com.example.weathertracker.ui.composables.CurrentWeatherDisplay
import com.example.weathertracker.ui.composables.SearchBar
import com.example.weathertracker.ui.composables.SearchResultCard

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel()
) {
    val uiState by remember { derivedStateOf { viewModel.uiState } }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        SearchBar(
            query = uiState.searchQuery,
            onQueryChange = viewModel::onSearchQueryChanged,
            onSearch = {}
        )
        Spacer(Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            when {
                uiState.searchResult != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SearchResultCard(
                            weather = uiState.searchResult!!,
                            onSelect = {
                                viewModel.onCitySelected()
                                keyboardController?.hide()
                            }
                        )
                    }
                }
                uiState.isCitySaved && uiState.currentWeather != null -> {
                    CurrentWeatherDisplay(weather = uiState.currentWeather!!)
                }
                else -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "No City Selected",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                                fontSize = 28.sp
                            ),
                            color = Color.Black
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = "Please Search For A City",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 18.sp
                            ),
                            color = Color.Black.copy(alpha = 0.7f)
                        )
                    }
                }
            }

            uiState.searchError?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 20.dp)
                )
            }

            if (uiState.isLoading) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }
    }
}
