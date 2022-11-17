package com.plcoding.weatherapp.presentation

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.plcoding.weatherapp.features.MyService

@Composable
fun ServiceScreen(
) {

    var text by remember { mutableStateOf("") }
    var textService by remember { mutableStateOf("info") }

    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = {
                Intent(context, MyService::class.java).also {
                    context.stopService(it)
                    textService = "service stop"
                }
            }) {
                Text(text = "STOP")
            }


            Button(onClick = {
                Intent(context, MyService::class.java).also {
                    context.startService(it)
                    textService = "service start"
                }
            }) {
                Text(text = "START")
            }
        }
        Text(
            text = textService,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        BasicTextField(
            value = text,
            onValueChange = {
                text = it
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .padding(10.dp)
                .onFocusChanged {
                }
        )
        Button(onClick = {
            Intent(context, MyService::class.java).also {
                val data = text
                it.putExtra("EXTRA_DATA",data)
                context.startService(it)
              }
        }) {
            Text(text = "Send data")
        }
    }
}