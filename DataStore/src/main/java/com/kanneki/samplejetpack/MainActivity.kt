package com.kanneki.samplejetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kanneki.samplejetpack.ui.theme.SampleJetpackTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleJetpackTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (viewModel.saveDataValue.isNotBlank()) {
                            Text(
                                text = stringResource(
                                    id = R.string.save_value_title,
                                    viewModel.saveDataValue
                                ),
                                style = MaterialTheme.typography.h5
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp),
                            value = viewModel.textValue,
                            label = { Text(text = "Data Value") },
                            onValueChange = (viewModel::setNewTextValue),
                            maxLines = 1
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        if (viewModel.sendValue.isNotBlank()) {
                            Text(
                                text = viewModel.sendValue,
                                style = MaterialTheme.typography.body2,
                                color = if (viewModel.sendValue == "Save Data") Color.Green else Color.Red
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        FilledTonalButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp),
                            onClick = { viewModel.sendData() }
                        ) {
                            Text(text = "Send")
                        }
                    }
                }
            }
        }
    }
}
