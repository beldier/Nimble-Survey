package com.nimble.survey.ui.screens.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.nimble.domain.AppError
import com.nimble.survey.R


@Composable
fun SimpleDialog(error: AppError, onDismiss: () -> Unit) {
    Column {
        val openDialog = remember { mutableStateOf(true) }

        if (openDialog.value) {

            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                    onDismiss()
                },
                title = {
                    Text(text = stringResource(R.string.survey_error))
                },
                text = {
                    Text(error.message)
                },
                confirmButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                            onDismiss()
                        }) {
                        Text(stringResource(R.string.ok))
                    }
                },

                )
        }
    }
}