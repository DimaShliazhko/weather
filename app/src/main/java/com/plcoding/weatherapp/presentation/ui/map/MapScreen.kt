package com.plcoding.weatherapp.presentation.ui.map

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker

@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val state = viewModel.state.value
    val uiSettings = remember { MapUiSettings(zoomControlsEnabled = false) }
    Scaffold(
        modifier = Modifier.padding(bottom = 60.dp),
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(
                    imageVector = if (state.isFalloutMap) {
                        Icons.Default.Done
                    } else {
                        Icons.Default.Close
                    },
                    contentDescription = null
                )
            }
        }
    ) {

        GoogleMap(
            modifier = modifier.fillMaxWidth(),
            properties = state.mapProperties,
            uiSettings = uiSettings,
            onMapLongClick = {
                viewModel.setEvent(MapEvents.OnMapsLongClick(it))
            }
        ) {
            state.mapPoints.forEach { point ->
                Marker(
                    position = LatLng(point.lat, point.lng),
                    title = "Metric",
                    snippet = "Long click to delete",
                    onInfoWindowClick = {
                        viewModel.setEvent(MapEvents.OnInfoWindowLongClick(point))
                    },
                    onClick = {
                        it.showInfoWindow()
                        true
                    },
                    icon = BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_AZURE
                    )

                )
            }
        }
    }
}