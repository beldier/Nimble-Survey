package com.nimble.survey.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nimble.survey.ui.screens.HomeScreen
import com.nimble.survey.ui.screens.login.LoginScreen
import com.nimble.survey.ui.screens.SurveyScreen

@Composable
fun Navigation(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = NavCommand.Login.baseRoute
    ) {
        homeNav(navController)
        loginNav(navController)
        surveyNav(navController)
    }
}


private fun NavGraphBuilder.loginNav(
    navController: NavController,
) {
    composable(NavCommand.Login) {
        LoginScreen(){
            navController.navigate(NavCommand.Survey.baseRoute)
        }
    }
}

private fun NavGraphBuilder.homeNav(
    navController: NavController,
) {
    composable(NavCommand.Home) {
        HomeScreen()
    }
}


private fun NavGraphBuilder.surveyNav(
    navController: NavController,
) {
    composable(NavCommand.Survey) {
        SurveyScreen()
    }
}


private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navCommand.baseRoute,
        arguments = navCommand.args
    ) {
        content(it)
    }
}