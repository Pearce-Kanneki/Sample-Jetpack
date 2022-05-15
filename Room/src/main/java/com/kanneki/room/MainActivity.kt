package com.kanneki.room

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.kanneki.room.domain.model.ShowData
import com.kanneki.room.ui.theme.SampleJetpackTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleJetpackTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RoomPage(viewModel)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomPage(viewModel: MainViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Sample Room")
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = "add"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Build,
                            contentDescription = "test"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            IconButton(
                onClick = { viewModel.onEvent(Event.Add) },
                modifier = Modifier.background(Color.Red)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (viewModel.items.isEmpty()) {
                Text(text = "No Data", style = MaterialTheme.typography.displayLarge)
            }
            
            LazyColumn (modifier = Modifier.fillMaxSize()){
                items(items = viewModel.items, key = { it.uuid }) { data ->
                    ItemCard(data = data)
                }
            }
        }

    }
}

@Composable
fun ItemCard(data: ShowData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = data.title,
            color = Color.Red,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = data.desc,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = data.uuid.toString(),
            style = MaterialTheme.typography.labelSmall
        )
        Spacer(modifier = Modifier
            .height(1.dp)
            .background(Color.Black))

    }
}
