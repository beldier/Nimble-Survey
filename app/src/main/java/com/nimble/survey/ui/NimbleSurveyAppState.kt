package com.nimble.survey.ui

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nimble.survey.ui.navigation.NavCommand
import com.nimble.survey.ui.navigation.navigatePoppingUpToStartDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberNimbleSurveyAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): NimbleSurveyAppState = remember(scaffoldState, navController, coroutineScope) {
    NimbleSurveyAppState(scaffoldState, navController, coroutineScope)
}

class NimbleSurveyAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
) {


    companion object {
        val BOTTOM_NAV_OPTIONS = listOf(NavCommand.Login, NavCommand.Home, NavCommand.Survey)

    }

    val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route
            ?: ""



    val showBottomNavigation: Boolean
        @Composable get() = BOTTOM_NAV_OPTIONS.any { currentRoute.contains(it.baseRoute) }


    fun onUpClick() {
        navController.popBackStack()
    }

    fun onMenuClick() {
        coroutineScope .launch { scaffoldState.drawerState.open() }
    }

    fun onNavItemClick(navCommand: NavCommand) {
        navController.navigatePoppingUpToStartDestination(navCommand.baseRoute)
    }

}

