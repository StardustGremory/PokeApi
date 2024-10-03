package com.example.pokemon.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.pokemon.R

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Body(Modifier.align(Alignment.Center), loginViewModel)
    }
}

@Composable
fun Body(modifier: Modifier, loginViewModel: LoginViewModel) {
    val email : String by loginViewModel.email.observeAsState(initial = "ing.franciscov@outlook.com")
    val pwd: String by loginViewModel.password.observeAsState(initial = "PokeApi.1234")
    Column(modifier = modifier) {
        ImageLogo(modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        EmailCompose(email = email)
        Spacer(modifier = Modifier.size(4.dp))
        PwdCompose(pwd = pwd)
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(loginViewModel)
    }
}

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.firebase),
        contentDescription = "Logo",
        modifier = modifier
    )
}

@Composable
fun EmailCompose(email: String) {
    TextField(
        enabled = false,
        value = email,
        onValueChange = {  },
        modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp),
        placeholder = { Text(text = "Email") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    )
}

@Composable
fun PwdCompose(pwd: String) {
    var pwdVisibility by remember {
        mutableStateOf(false)
    }
    TextField(
        enabled = false,
        value = pwd,
        onValueChange = {  },
        modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp),
        placeholder = { Text(text = "Password") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (pwdVisibility){
                Icons.Filled.Visibility
            }else{
                Icons.Filled.VisibilityOff
            }
            IconButton(onClick = { pwdVisibility = !pwdVisibility }) {
                Icon(imageVector = image, contentDescription = "show password")
            }
        },
        visualTransformation = if(pwdVisibility){
            VisualTransformation.None
        }else{
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun LoginButton(loginViewModel: LoginViewModel) {
    val context = LocalContext.current
    Button(onClick = { loginViewModel.loginFirebase(context) },
        enabled = true,
        modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4EA8E9),
            disabledContainerColor = Color(0xFF4EA8E9),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Login")
    }
}