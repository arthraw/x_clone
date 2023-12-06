package com.project.loginscreen.presentation.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.loginscreen.R
import com.project.loginscreen.presentation.Screen
import com.project.loginscreen.presentation.components.AlertDialogBox
import com.project.loginscreen.presentation.theme.LoginScreenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LoginScreenTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()
                        setUpNavHost(navController = navController)
                    }
                }
            }
        }
    }

}
@Composable
fun PasswordCheckLoader(navController: NavHostController, name: String?) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var isFocused by remember { mutableStateOf(false) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var isValid by remember { mutableStateOf(false) }
    var validFormNameFlag by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (validFormNameFlag) {
            AlertDialogBox(
                text = "Por favor insira uma senha valida.",
                openDialog = validFormNameFlag,
                onDismiss = {
                    validFormNameFlag = false
                }
            )
        }
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.padding(top = 10.dp))

            Image(
                painter = painterResource(R.drawable.x_logo),
                contentDescription = "X social media Logo",
                modifier = Modifier
                    .size(70.dp)
                    .padding(top = 30.dp),
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row {
            welcomeText(
                text = "Digite sua senha",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            blockLabelName(
                label = name.toString()
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(5.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = text,
                        label = {
                            Text(
                                text = "Senha",
                                fontWeight = FontWeight.Light
                            )
                        },
                        maxLines = 1,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        onValueChange = { input: TextFieldValue ->
                            val changeValue: String = input.text.ifBlank {
                                input.text.toString()
                            }
                            text = input.copy(
                                text = changeValue,
                                selection = TextRange(changeValue.length)
                            )
                        },
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(3.dp))
                            .border(
                                BorderStroke(
                                    0.8.dp,
                                    if (isFocused) Color(0xFF4FBEF0) else Color.Gray
                                )
                            )
                            .onFocusChanged {
                                isFocused = it.isFocused
                            },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.LightGray,
                            focusedContainerColor = Color.Black,
                            unfocusedContainerColor = Color.Black,
                            disabledContainerColor = Color.Black,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                        ),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val image = if (passwordVisible)
                                Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff

                            val description = if (passwordVisible) "Hide password" else "Show password"
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = image,
                                    contentDescription = description,
                                )
                            }
                        }
                    )
                }
            }
        }
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight()
        ) {
            bottomContent(
                modifier = Modifier
                    .padding(40.dp),
                text = "Entrar",
                showPassButton = false,
                showNextButton = true,
                accountMessage = "Não tem uma conta?",
                entry = "Inscreva-se",
                showText = true,
                createAccount = {
                    navController.navigate(Screen.SignUpScreen.route)
                },
                toFeed = {
                    isValid = text.text.isNotEmpty()

                    if (isValid) {
                        validFormNameFlag = false
                        navController.navigate(Screen.Feed.route)
                    } else {
                        validFormNameFlag = true
                    }
                },
            )
        }
    }
}
@SuppressLint("ComposableNaming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun blockLabelName(
    label: String,
) {
    val context = LocalContext.current
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var isFocused by remember { mutableStateOf(true) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = text,
                label = {
                    Text(
                        text = label,
                        fontWeight = FontWeight.Light
                    )
                },
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onValueChange = { input: TextFieldValue ->
                    val changeValue: String = input.text.ifBlank {
                        val toast =
                            Toast.makeText(context, "Conta nao encontrada", Toast.LENGTH_SHORT)
                        toast.show()
                        input.text.toString()

                    }
                    text = input.copy(
                        text = changeValue,
                        selection = TextRange(changeValue.length)
                    )
                },
                enabled = false,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(3.dp))
                    .border(BorderStroke(0.8.dp, if (isFocused) Color(0x6B5C5F61) else Color.Black))
                    .onFocusChanged {
                        isFocused = it.isFocused
                    },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    focusedContainerColor = Color(0x6B5C5F61),
                    unfocusedContainerColor = Color(0x6B5C5F61),
                    disabledContainerColor = Color(0x6B343738),
                    cursorColor = Color.White,
                    disabledIndicatorColor = Color(0x6B000000),
                    disabledLabelColor = Color.DarkGray,
                    disabledTextColor = Color.DarkGray,
                    disabledPlaceholderColor = Color.DarkGray
                ),
            )
        }
        Spacer(modifier = Modifier.padding(5.dp))
    }
}

