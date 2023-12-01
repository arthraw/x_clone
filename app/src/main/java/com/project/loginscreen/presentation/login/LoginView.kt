package com.project.loginscreen.presentation.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.loginscreen.R
import com.project.loginscreen.presentation.Screen
import com.project.loginscreen.presentation.components.AlertDialogBox
import com.project.loginscreen.presentation.components.RegisterConfirmation
import com.project.loginscreen.presentation.feed.FeedScreen
import com.project.loginscreen.presentation.theme.LoginScreenTheme
import kotlinx.coroutines.delay
import java.lang.Exception


class LoginView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var openDialog by remember { mutableStateOf(false) }
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
                        AlertDialogBox(
                            text = "Insira um nome ou crie uma conta clicando em 'inscreva-se'",
                            openDialog = openDialog,
                            onDismiss = {
                                openDialog = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun setUpNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {
        composable(
            route = Screen.LoginScreen.route,
            popEnterTransition = { slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left) },
            popExitTransition = { slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right) }
        ) {
            LoginScreenLoader(navController = navController)
        }
        composable(
            route = Screen.SignUpScreen.route,
            enterTransition = { slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Start) },
            exitTransition = { slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Start) }
        ) {
            SignUpScreenLoader(navController = navController)
        }
        composable(
            route = Screen.PasswordScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            ) ,
            enterTransition = { slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Start) },
            exitTransition = { slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Start) }
        ) {entry ->
            PasswordCheckLoader(navController = navController, name = entry.arguments?.getString("name"))
        }
        composable(
            route = Screen.Success.route,
            enterTransition = { slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Start) },
            exitTransition = { slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Start) }
        ) {
            RegisterConfirmation(navController = navController)
        }
        composable(
            route = Screen.Feed.route,
            enterTransition = { slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Start) },
            exitTransition = { slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Start) }
        ) {
            FeedScreen()
        }
    }
}

@Composable
fun LoginScreenLoader(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Image(
                painter = painterResource(R.drawable.x_logo),
                contentDescription = "X social media Logo",
                modifier = Modifier
                    .size(70.dp)
                    .padding(top = 25.dp),
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row {
            welcomeText(
                text = "Entrar no X",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
            )
        }
        Spacer(modifier = Modifier.padding(30.dp))
        Row {
            socialMediaLogin(
                modifier = Modifier.width(250.dp),
                google = "   Fazer login com o Google",
                apple = "Entrar com Apple",
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier
                    .weight(1f),
            )
            Text(
                text = "ou",
                color = Color.White,
                modifier = Modifier
                    .background(color = Color(0xFF000000))
                    .padding(horizontal = 8.dp)
            )
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier
                    .weight(1f),
            )
        }
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            loginLabel(
                modifier = Modifier
                    .padding(10.dp),
                label = "E-mail ou nome de usuário",
                accountMessage = "Não tem uma conta?",
                entry = "Inscreva-se",
                navController = navController
            )
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun welcomeText(
    text: String,
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = text,
                modifier = Modifier.align(Alignment.Bottom),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp
            )
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
private fun socialMediaLogin(
    modifier: Modifier,
    google: String,
    apple: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            Button(
                onClick = {  },
                modifier = modifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFFFFF),
                )
            ) {
                Box(
                    modifier = modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Icon(
                        painter = painterResource(R.drawable.google_icon),
                        modifier = Modifier
                            .size(18.dp),
                        contentDescription = "Google login icon",
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(55.dp))
                    Text(
                        text = google,
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        Spacer(modifier = Modifier.padding(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = modifier,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFFFFF),
                )
            ) {
                Box(
                    modifier = modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Icon(
                        painter = painterResource(R.drawable.apple_icon),
                        modifier = Modifier
                            .size(18.dp),
                        contentDescription = "Apple login icon",
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(30.dp))
                    Text(
                        text = apple,
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@SuppressLint("ComposableNaming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginLabel(
    modifier: Modifier,
    label: String,
    keyPass: Boolean = true,
    keyNext: Boolean = true,
    accountMessage: String = "",
    entry: String = "",
    showText: Boolean = true,
    navController: NavController
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current
    var isFocused by remember { mutableStateOf(false) }
    var validFormNameFlag by remember { mutableStateOf(false) }
    var isValid by remember { mutableStateOf(false) }

    Column(modifier) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (validFormNameFlag) {
                AlertDialogBox(
                    text = "Insira um nome ou crie uma conta clicando em 'inscreva-se'.",
                    openDialog = validFormNameFlag,
                    onDismiss = {
                        validFormNameFlag = false
                    }
                )
            }
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
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(3.dp))
                    .border(BorderStroke(0.8.dp, if (isFocused) Color(0xFF4FBEF0) else Color.Gray))
                    .onFocusChanged {
                        isFocused = it.isFocused
                    },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    focusedContainerColor = Color.Black,
                    unfocusedContainerColor = Color.Black,
                    disabledContainerColor = Color.Black,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
            )
        }
        Spacer(modifier = Modifier.padding(15.dp))
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                bottomContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .align(Alignment.Center),
                    text = "Avançar",
                    password = "Esqueceu sua senha?",
                    showPassButton = keyPass,
                    showNextButton = keyNext,
                    accountMessage = accountMessage,
                    entry = entry,
                    showText = showText,
                    createAccount = {
                        navController.navigate(Screen.SignUpScreen.route)
                    },
                    toFeed = {
                        isValid = text.text.isNotEmpty()
                        if (isValid) {
                            validFormNameFlag = false
                            navController.navigate(Screen.PasswordScreen.withArgs(text.text))
                        }
                        else {
                            validFormNameFlag = true
                        }
                    },
                )
            }
        }

    }
}
@SuppressLint("ComposableNaming")
@Composable
fun bottomContent(
    modifier: Modifier,
    text: String = "",
    password: String = "",
    showPassButton: Boolean,
    showNextButton: Boolean,
    accountMessage: String,
    entry: String,
    showText: Boolean,
    createAccount: () -> Unit,
    toFeed: () -> Unit,

) {
    Column(modifier) {
        if (showNextButton) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { toFeed() },
                    modifier = Modifier
                        .height(40.dp)
                        .border(
                            color = Color.Black,
                            width = 0.dp,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFFFFF),
                    ),
                ) {
                    Text(
                        text = text,
                        color = Color(0xFF000000),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
        if (showPassButton) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(40.dp)
                        .border(
                            color = Color.White,
                            width = 0.5.dp,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF000000),
                    ),
                ) {
                    Text(
                        text = password,
                        modifier = Modifier,
                        color = Color(0xFFFFFFFF),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
        if (showText) {
            Spacer(modifier = Modifier.padding(10.dp))
            Row(
                modifier = Modifier.padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = accountMessage,
                    color = Color.Gray,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(8.dp))

                ClickableText(
                    text = AnnotatedString(text = entry),
                    onClick = { createAccount() },
                    style = TextStyle(color = Color(0xFF17A9EC), fontSize = 16.sp),
                )
            }
        }
    }
}
