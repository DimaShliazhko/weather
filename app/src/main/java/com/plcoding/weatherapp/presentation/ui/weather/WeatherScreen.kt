package com.plcoding.weatherapp.presentation.ui.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.plcoding.weatherapp.presentation.InternetConnection
import com.plcoding.weatherapp.presentation.NoPermissonView
import com.plcoding.weatherapp.presentation.Toolbar
import com.plcoding.weatherapp.presentation.ui.LocalSpacing
import com.plcoding.weatherapp.presentation.ui.spacing
import com.plcoding.weatherapp.presentation.ui.theme.PrimaryBlueDark
import com.plcoding.weatherapp.presentation.ui.theme.SecondaryBlueDeepDark
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel(),
    viewModel16: Weather16DaysViewModel = hiltViewModel()

) {
    val state = viewModel.state.value
    val state16Days = viewModel16.state.value
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState(
        initialPage = 0,
    )
    val currentTabIndex = pagerState.currentPage
    val tabs = listOf("Today", "16 days")

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PrimaryBlueDark)
        ) {

            Toolbar(
                state = state,
                onSearch = {}
            )

            state.weatherInfo?.currentWeatherData?.let {
                TabRow(
                    selectedTabIndex = currentTabIndex,
                    backgroundColor = SecondaryBlueDeepDark
                ) {
                    tabs.forEachIndexed { index, tab ->
                        Tab(
                            selected = index == currentTabIndex,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            text = { Text(text = tab, color = Color.White) }
                        )
                    }
                }
            }

            HorizontalPager(
                state = pagerState,
                count = tabs.size,
                modifier = Modifier.weight(1f)
            ) { index ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    when (index) {
                        0 -> {
                            Column(
                                modifier = Modifier
                                    .weight(1f, false)
                                    .verticalScroll(scrollState)
                            ) {
                                WeatherCard(state = state, background = SecondaryBlueDeepDark)
                                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                                WeatherForecast(state = state)
                            }
                        }
                        1 -> {
                            Days16(
                                state = state16Days,
                                background = SecondaryBlueDeepDark
                            )
                        }
                    }
                }
            }
        }
    }

    if (state.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                color = Color.Blue
            )
        }
    }

    state.error?.let { error ->
        NoPermissonView(error)
    }

    if (!state.isHasInternetConnection) {
        InternetConnection()
    }
}