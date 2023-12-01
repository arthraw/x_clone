package com.project.loginscreen.presentation.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.loginscreen.R
import com.project.loginscreen.presentation.Screen
import com.project.loginscreen.presentation.login.setUpNavHost
import com.project.loginscreen.presentation.theme.LoginScreenTheme

class RegisterConfirmation : ComponentActivity() {
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
fun RegisterConfirmation(navController : NavController) {
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
                    .size(45.dp)
                    .padding(top = 20.dp),
            )
        }
        Spacer(modifier = Modifier.padding(70.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ConfirmationDraw(
                navController = navController,
                modifier = Modifier
                    .size(65.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun ConfirmationDraw(
    modifier: Modifier,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.confirmation_register),
                contentDescription = "Account Created",
                modifier = modifier,
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Parabéns, sua conta foi criada com sucesso!",
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.padding(50.dp))
        Row (
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            toFeedButton(
                navController = navController
            )
        }
    }
}

@Composable
private fun toFeedButton(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
            ,
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    navController.navigate(Screen.Feed.route)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF03A9F4),
                ),
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp)
                    .border(
                        color = Color(0xFF03A9F4),
                        width = 0.5.dp,
                        shape = RoundedCornerShape(50.dp)
                    )
                ,
            ) {
                Text(
                    text = "Entrar",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    LoginScreenTheme {
        val navController = rememberNavController()
        RegisterConfirmation(navController)
    }
}