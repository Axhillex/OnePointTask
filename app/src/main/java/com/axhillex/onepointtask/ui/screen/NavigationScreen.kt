package com.axhillex.onepointtask.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.axhillex.onepointtask.data.DetailsScreen
import com.axhillex.onepointtask.data.ListScreen
import com.axhillex.onepointtask.vm.ItemViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    val viewModel = koinViewModel<ItemViewModel>()
    NavHost(navController = navController, startDestination = ListScreen) {
        composable<ListScreen> {
            val itemsList by viewModel.items.collectAsState()
            ListScreen(
                itemsList = itemsList,
                vm = viewModel
            )
        }
        composable<DetailsScreen> {

        }
    }
}