package com.plcoding.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.work.*
import coil.compose.rememberImagePainter
import com.plcoding.weatherapp.data.repository.ColorFilterWorker
import com.plcoding.weatherapp.data.repository.DownloadWorker
import com.plcoding.weatherapp.data.repository.WorkerParams
import com.plcoding.weatherapp.presentation.ui.spacing
import com.plcoding.weatherapp.presentation.ui.theme.PrimaryBlueDark

@Composable
fun ThirdScreen(
    modifier: Modifier = Modifier.background(PrimaryBlueDark)
) {
    val context = LocalContext.current
    val downloadRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
        .setConstraints(
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        )
        .build()

    val colorRequest = OneTimeWorkRequestBuilder<ColorFilterWorker>()
        .build()

    val workManager = WorkManager.getInstance(context)


    val workInfo = workManager
        .getWorkInfosForUniqueWorkLiveData("download")
        .observeAsState()
        .value

    val downloadInfo = remember(key1 = workInfo) {
        workInfo?.find { it.id == downloadRequest.id }
    }

    val filterInfo = remember(key1 = workInfo) {
        workInfo?.find { it.id == colorRequest.id }
    }

    val imageUri by derivedStateOf {
        val downloadUri = downloadInfo?.outputData?.getString(WorkerParams.IMAGE_URI)?.toUri()
        val filterUri = filterInfo?.outputData?.getString(WorkerParams.FILTER_URI)?.toUri()
        filterUri ?: downloadUri
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        imageUri?.let { uri ->
            Image(
                painter = rememberImagePainter(
                    data = uri
                ),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        }
        Button(
                     onClick = {
                workManager.beginUniqueWork(
                    "download",
                    ExistingWorkPolicy.KEEP,
                    downloadRequest
                )
                    .then(colorRequest)
                    .enqueue()
            },
            enabled = downloadInfo?.state != WorkInfo.State.RUNNING
        ) {
            Text(text = "Start Download")
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        when (downloadInfo?.state) {
            WorkInfo.State.RUNNING -> Text(text = "Downloading...")
            WorkInfo.State.SUCCEEDED -> Text(text = "Downloading SUCCEEDED...")
            WorkInfo.State.FAILED -> Text(text = "Downloading FAILED...")
            WorkInfo.State.CANCELLED -> Text(text = "Downloading CANCELLED...")
            WorkInfo.State.BLOCKED -> Text(text = "Downloading BLOCKED...")

            else -> Unit
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        when (filterInfo?.state) {
            WorkInfo.State.RUNNING -> Text(text = "Applying filter...")
            WorkInfo.State.SUCCEEDED -> Text(text = "Applying filter SUCCEEDED...")
            WorkInfo.State.FAILED -> Text(text = "Applying filter FAILED...")
            WorkInfo.State.CANCELLED -> Text(text = "Applying filter CANCELLED...")
            WorkInfo.State.BLOCKED -> Text(text = "Applying filter BLOCKED...")
            else -> Unit
        }
    }
}
