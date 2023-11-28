package com.nimble.survey.ui.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.nimble.survey.R
import com.nimble.survey.ui.screens.shared.SimpleDialog
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit,
) {
    val dataState by viewModel.dataState.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    LoginScreenContent(
        uiState = uiState,
        dataState = dataState,
        dismissDialog = { viewModel.dismissDialog() },
        updateEmail = { viewModel.updateEmail(it) },
        updatePassword = { viewModel.updatePassword(it) },
        performLogin = { viewModel.performLogin(onLoginSuccess) })

}

@Composable
fun LoginScreenContent(
    uiState: LoginViewModel.UIState,
    dataState: LoginViewModel.DataState,
    dismissDialog: () -> Unit,
    updateEmail: (String) -> Unit,
    updatePassword: (String) -> Unit,
    performLogin: () -> Unit,
) {
    uiState.appError?.let {
        SimpleDialog(it) { dismissDialog }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        Image(
            painterResource(R.drawable.background),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()

                .blur(50.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = createGradientBrush(
                        listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.8f)
                        ), true
                    )
                )
                .align(Alignment.Center)
        )
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
        ) {
            val (logo, loginForm) = createRefs()
            Image(

                painter = painterResource(id = R.drawable.logo_nimble),
                contentDescription = stringResource(R.string.nimble_logo),
                modifier = Modifier
                    .height(40.dp)
                    .width(167.dp)
                    .constrainAs(logo) {
                        centerHorizontallyTo(parent)
                        bottom.linkTo(loginForm.top, margin = 90.dp)
                    }
            )

            Column(

                modifier = Modifier.constrainAs(loginForm) {
                    centerHorizontallyTo(parent)
                    centerVerticallyTo(parent)
                },
                verticalArrangement = Arrangement.spacedBy(10.dp)

            ) {
                CustomLoginTextField(
                    text = dataState.email,
                    placeHolder = stringResource(R.string.email),
                    onChange = { updateEmail(it) }
                )
                CustomLoginTextField(
                    text = dataState.password,
                    placeHolder = stringResource(R.string.password),
                    onChange = { updatePassword(it) },
                    isPassword = true
                )
                Button(
                    onClick = {
                        performLogin()
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
    }

}

fun createGradientBrush(
    colors: List<Color>,
    isVertical: Boolean = true
): Brush {

    val endOffset = if (isVertical) {
        Offset(0f, Float.POSITIVE_INFINITY)
    } else {
        Offset(Float.POSITIVE_INFINITY, 0f)
    }

    return Brush.linearGradient(
        colors = colors,
        start = Offset(0f, 0f),
        end = endOffset,
        tileMode = TileMode.Clamp
    )
}

@Preview(device = Devices.PIXEL_2_XL)
@Composable
fun LoginScreenPreview() {
    LoginScreenContent(
        uiState = LoginViewModel.UIState(),
        dataState = LoginViewModel.DataState(),
        dismissDialog = { /*TODO*/ },
        updateEmail = {/*TODO*/ },
        updatePassword = {/*TODO*/ },
        performLogin = {/*TODO*/ },
    )
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
            Text(text = placeHolder, color = Color.Gray)
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
