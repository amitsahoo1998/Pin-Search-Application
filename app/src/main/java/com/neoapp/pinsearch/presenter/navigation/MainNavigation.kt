package com.neoapp.pinsearch.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neoapp.pinsearch.presenter.screens.HistoryScreen
import com.neoapp.pinsearch.presenter.screens.HomeScreen
import com.neoapp.pinsearch.presenter.screens.PinSearchScreen
import com.neoapp.pinsearch.presenter.screens.PostOfficeSearchScreen
import com.neoapp.pinsearch.presenter.screens.Screen

const val NAV_HOST_ROUTE = "app-main-route"

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.Home.route, route = NAV_HOST_ROUTE) {
        composable(Screen.Home.route) {
            HomeScreen(
                navigateToPinSearch = { navController.navigateToPinScreen() },
                navigateToPostOffice = { navController.navigateToPostOfficeScreen() },
                navigateToHistoryScreen = {navController.navigateToHistoryScreen()})
        }
        composable(Screen.Pin.route) {
            PinSearchScreen(
                viewModel = hiltViewModel(),
                navigateToHistoryScreen = { navController.navigateToHistoryScreen() })
        }
        composable(Screen.Post.route) {
            PostOfficeSearchScreen(viewModel = hiltViewModel(),navigateToHistoryScreen = { navController.navigateToHistoryScreen() })
        }
        composable(Screen.History.route) {
            HistoryScreen(viewModel = hiltViewModel())
        }
    }
}

fun NavController.navigateToPinScreen() = navigate(Screen.Pin.route)

fun NavController.navigateToPostOfficeScreen() = navigate(Screen.Post.route)

fun NavController.navigateToHistoryScreen() = navigate(Screen.History.route)