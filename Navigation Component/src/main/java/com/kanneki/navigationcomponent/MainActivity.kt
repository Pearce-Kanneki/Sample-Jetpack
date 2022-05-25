package com.kanneki.navigationcomponent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kanneki.navigationcomponent.presenration.detail.DetailScreen
import com.kanneki.navigationcomponent.presenration.main.MainScreen
import com.kanneki.navigationcomponent.presenration.main.MainViewModel
import com.kanneki.navigationcomponent.presenration.normal.NormalScreen
import com.kanneki.navigationcomponent.ui.theme.SampleJetpackTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleJetpackTheme {
                val navController = rememberNavController()

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Navigation Component",
                                    modifier = Modifier.fillMaxWidth(),
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = { navController.navigateUp() }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "back"
                                    )
                                }
                            }
                        )
                    }
                ) {
                    ActivityScreen(navController = navController)
                }
            }
        }
    }
}

@Composable
fun ActivityScreen(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {

        composable(route = Screen.MainScreen.route) {
            val viewModel = viewModel<MainViewModel>()
            MainScreen(navController = navController, viewModel)
        }
        composable(route = Screen.NormalScreen.route) {
            NormalScreen()
        }
        composable(route = Screen.DetailScreen.route + "/{message}") {
            DetailScreen(title = it.arguments?.getString("message") ?: "Not Value")
        }
    }
}

