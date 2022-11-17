package com.plcoding.weatherapp.presentation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.weatherapp.presentation.ui.NavigationScreens
import com.plcoding.weatherapp.presentation.ui.spacing
import com.plcoding.weatherapp.presentation.ui.theme.PrimaryBlueDark
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FlowScreen(
    modifier: Modifier = Modifier.background(PrimaryBlueDark),
    viewModel: FlowViewModel = hiltViewModel(),
    navController: NavController,
) {

    var text by remember { mutableStateOf("") }
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed,
        animationSpec = SpringSpec(
            dampingRatio = Spring.DampingRatioHighBouncy
        )
    )

    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    val coroutineScope = rememberCoroutineScope()
    BottomSheetScaffold(
        modifier = Modifier.clip(RoundedCornerShape(5.dp)),
        scaffoldState = scaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .height(300.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "it is bottom sheet", fontSize = 30.sp)
            }
        },
        sheetPeekHeight = 0.dp,
        sheetBackgroundColor = Color.Green
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                Button(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    onClick = {
                        viewModel.showNotification()
                    }
                ) {
                    Text(text = "Show notification")
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                Button(
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    onClick = {
                        coroutineScope.launch {
                            if (sheetState.isCollapsed) {
                                sheetState.expand()
                            } else {
                                sheetState.collapse()
                            }

                        }
                    }
                ) {
                    Text(text = "Show sheet")
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                TextField(
                    value = text, onValueChange = { text = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                )

                Button(onClick = {
                    navController.navigate(NavigationScreens.DetailScreen.withArgs(text))
                }) {
                    Text(text = "Open New Activity")
                }

                Button(onClick = {
                    navController.navigate(NavigationScreens.ServiceScreen.route)
                }) {
                    Text(text = "Service")
                }
            }

        }
    }
}