package com.nimble.survey.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavCommand(
    internal val feature: Feature,
    private val navArgs: List<NavArg> = emptyList(),
) {
    object Login: NavCommand(Feature.LOGIN)

    object Home : NavCommand(Feature.HOME)


    object Survey: NavCommand(Feature.SURVEY)


    val baseRoute = run {
        val argValues = navArgs.map { "{${it.key}}" }
        listOf(feature.route)
            .plus(argValues)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

}

enum class NavArg(val key: String, val navType: NavType<*>) {

}