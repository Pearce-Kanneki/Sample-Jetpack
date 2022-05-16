package com.kanneki.roomandhilt

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kanneki.roomandhilt.domain.model.ShowData
import com.kanneki.roomandhilt.ui.theme.SampleJetpackTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleJetpackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RoomPage()
                }
            }
        }
    }
}

@Composable
fun RoomPage(viewModel: MainViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Sample Room")
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "test"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onEvent(Event.Add) },
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add",
                    tint = Color.White
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
                Text(text = "No Data", style = MaterialTheme.typography.h4)
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items = viewModel.items, key = { it.uuid }) { data ->
                    ItemCard(data = data) {
                        viewModel.selectData.value = it
                        viewModel.showDialog.value = true
                    }
                }
            }

            if (viewModel.showDialog.value) {
                MessageDialog(viewModel.selectData.value?.uuid?.toString() ?: "") { result ->
                    viewModel.showDialog.value = false

                    if (result) {
                        viewModel.onEvent(Event.Delete)
                    }
                }
            }
        }

    }
}

@Composable
fun ItemCard(data: ShowData, onClick: (ShowData) -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick(data) },
    ) {
        Text(
            text = data.title,
            color = Color.Red,
            style = MaterialTheme.typography.subtitle2
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = data.desc,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = data.uuid.toString(),
            style = MaterialTheme.typography.body1
        )
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .background(Color.Black)
        )

    }
}

@Composable
fun MessageDialog(deleteId: String, onClick: (Boolean) -> Unit = {}) {
    AlertDialog(
        title = { Text(text = "提示") },
        text = { Text(text = "Delete uuid: $deleteId") },
        onDismissRequest = { /*TODO*/ },
        confirmButton = {
            TextButton(onClick = { onClick(true) }) {
                Text(text = stringResource(id = R.string.ok))
            }
        },
        dismissButton = {
            TextButton(onClick = { onClick(false) }) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PreViewItemCard() {
    val data = ShowData(
        uuid = 1,
        title = "Test",
        desc = "Abc"
    )
    ItemCard(data = data)
}

@Preview
@Composable
fun PreviewDialog() {
    MessageDialog("Test")
}