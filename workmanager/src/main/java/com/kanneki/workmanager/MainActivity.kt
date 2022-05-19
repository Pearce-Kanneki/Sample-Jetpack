package com.kanneki.workmanager

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.kanneki.workmanager.ui.theme.SampleJetpackTheme
import com.kanneki.workmanager.workmanager.CoroutineMyWorker
import com.kanneki.workmanager.workmanager.LogWorker
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val logWorker: WorkRequest = OneTimeWorkRequestBuilder<LogWorker>().build()
    private val cWorker: WorkRequest = OneTimeWorkRequestBuilder<CoroutineMyWorker>().build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleJetpackTheme {
                val context = LocalContext.current

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Greeting(name = "World")
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(onClick = { getWork(context) }) {
                            Text(text = "One Time Work")
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(onClick = { getCoroutineWork(context) }) {
                            Text(text = "Periodic Work")
                        }
                    }
                }
            }
        }
    }

    private fun getWork(context: Context) {
        WorkManager.getInstance(context)
            .enqueue(logWorker)
    }

    private fun getCoroutineWork(context: Context) {
        lifecycleScope.launch {
            WorkManager.getInstance(context)
                .enqueue(cWorker)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SampleJetpackTheme {
        Greeting("Android")
    }
}