package com.kanneki.navigationcomponent

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object DetailScreen: Screen("detail_screen")
    object NormalScreen: Screen("normal_screen")
}
