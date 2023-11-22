package com.nimble.survey.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.nimble.survey.ui.navigation.Navigation
import com.nimble.survey.ui.theme.NimbleSurveyTheme

@Composable
fun NimbleSurveyApp() {
    val appState = rememberNimbleSurveyAppState()
    NimbleSurveyScreen {
        Scaffold(
            backgroundColor = Color.Black
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(appState.navController)
            }
        }

    }
}

@Composable
fun NimbleSurveyScreen(content: @Composable () -> Unit) {
    NimbleSurveyTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}