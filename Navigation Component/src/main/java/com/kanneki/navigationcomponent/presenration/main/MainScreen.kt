package com.kanneki.navigationcomponent.presenration.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp),
            value = viewModel.textValue,
            label = { Text(text = "Value")},
            onValueChange = (viewModel::setNewTextValue)
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 16.dp),
            onClick = {
                navController.navigate(viewModel.checkScreen())
            }
        ) {
            Text(text = "Send")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreView() {
    MainScreen(
        navController = rememberNavController(),
        viewModel = MainViewModel()
    )
}