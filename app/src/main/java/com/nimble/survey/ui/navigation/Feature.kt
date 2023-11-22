package com.nimble.survey.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.nimble.survey.R

enum class Feature(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    LOGIN("login", R.string.login, Icons.Filled.Person),
    HOME("home", R.string.home, Icons.Filled.Home),
    SURVEY("survey", R.string.survey, Icons.Filled.Create),
}