package com.jetpackcopmosedemo.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jetpackcopmosedemo.data.UiState
import com.jetpackcopmosedemo.presentation.theme.JetpackComposeDemoTheme
import com.jetpackcopmosedemo.utils.CommonCircularIndicator

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val configuration = LocalConfiguration.current
    val context = LocalContext.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Surface {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            viewModel.uiStateMockListEvents.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        CommonCircularIndicator()
                    }

                    is UiState.Success -> {
                        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                            items(uiState.data.size, key = { index -> index }) { index ->
                                Box(
                                    modifier = Modifier
                                        .size(width = screenWidth / 4, height = screenHeight / 4)
                                        .padding(16.dp)
                                        .background(MaterialTheme.colorScheme.inversePrimary)
                                        .clickable(onClick = {
                                            viewModel.deleteEvents(index)
                                        })
                                ) {
                                    Text(
                                        text = "Hello ${uiState.data[index]}!",
                                        modifier = Modifier.align(Alignment.Center),
                                        style = MaterialTheme.typography.titleLarge,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Column(
                                        verticalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        ElevatedButton(onClick = { viewModel.deleteEvents(index) }) {
                                            Text(
                                                text = "Delete",
                                                style = MaterialTheme.typography.titleLarge,
                                                color = MaterialTheme.colorScheme.inversePrimary
                                            )
                                        }
                                        ElevatedButton(onClick = { viewModel.updateEvents(index) }) {
                                            Text(
                                                text = "Edit",
                                                style = MaterialTheme.typography.titleLarge,
                                                color = MaterialTheme.colorScheme.inversePrimary
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    is UiState.Error -> {
                        Text(
                            text = "Item not found",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.inversePrimary
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreviewPreview() {
    JetpackComposeDemoTheme {
        val navController: NavHostController = rememberNavController()
        HomeScreen(navController)
    }
}
