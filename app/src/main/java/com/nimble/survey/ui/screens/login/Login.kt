package com.nimble.survey.ui.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nimble.survey.R
import com.nimble.survey.ui.screens.shared.SimpleDialog
import org.koin.androidx.compose.koinViewModel

@Preview(device = Devices.PIXEL_2_XL)
@Composable
fun LoginScreen(viewModel: LoginViewModel = koinViewModel()) {
    val dataState by viewModel.dataState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    uiState.appError?.let {
        SimpleDialog(it) { viewModel.dismissDialog() }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray)
            .padding(horizontal = 8.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_nimble),
            contentDescription = stringResource(R.string.nimble_logo),
            modifier = Modifier
                .height(40.dp)
                .width(167.dp),
        )

        CustomLoginTextField(
            text = dataState.email,
            placeHolder = "Email",
            onChange = { viewModel.updateEmail(it) }
        )
        CustomLoginTextField(
            text = dataState.password,
            placeHolder = "PassWord",
            onChange = { viewModel.updatePassword(it) },
            isPassword = true
        )
        Button(
            onClick = {
                viewModel.performLogin()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .navigationBarsPadding()
                .imePadding(),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
            ),

            ) {
            Text(
                text = stringResource(R.string.login).uppercase(),
                color = Color.Black
            )
        }
    }
}


@Composable
fun CustomLoginTextField(
    text: String,
    placeHolder: String,
    onChange: (text: String) -> Unit,
    isPassword: Boolean = false
) {
    TextField(
        value = text,
        onValueChange = {
            onChange(it)
        },
        placeholder = {
            Text(text = placeHolder, modifier = Modifier.background(Color.Gray))
        },
        modifier = Modifier
            .border(
                BorderStroke(width = 2.dp, color = Color.White),
                shape = RoundedCornerShape(12)
            )
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        maxLines = 1
    )
}
