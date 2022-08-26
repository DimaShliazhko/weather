package com.plcoding.weatherapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.plcoding.weatherapp.presentation.ui.BottomNavItem
import com.plcoding.weatherapp.presentation.ui.theme.SecondaryBlueDeepDark

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClickListener: (BottomNavItem) -> Unit

) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        elevation = 5.dp,
        backgroundColor = SecondaryBlueDeepDark
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClickListener(item) },
                selectedContentColor = Color.DarkGray,
                unselectedContentColor = Color.LightGray,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if (item.badgeCount > 0) {
                            BadgedBox(
                                badge = {
                                    Text(
                                        text = item.badgeCount.toString(),
                                        fontSize = 10.sp
                                    )
                                }
                            ) {
                                Icon(imageVector = item.icon, contentDescription = item.name)

                            }

                        } else {
                            Icon(imageVector = item.icon, contentDescription = item.name)
                            Text(
                                text = item.name,
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center
                            )
                        }

                    }
                }
            )
        }
    }
}