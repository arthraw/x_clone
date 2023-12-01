package com.project.loginscreen.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AlertDialogBox(
    text: String,
    openDialog: Boolean,
    onDismiss: () -> Unit

) {
    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                onDismiss()
            },
            tonalElevation = 20.dp,
            containerColor = Color(0xFF151618),
            title = {
                Text(
                    text = "Verifique seus dados.",
                    color = Color(0xFFFFFFFF),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            },
            text = {
                Text(
                    text = text,
                    color = Color(0xFFFFFFFF),
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onDismiss()
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .border(
                            color = Color(0xFF151618),
                            width = 0.dp,
                            shape = RoundedCornerShape(50.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF03A9F4),
                    ),
                ) {
                    Text("Fechar")
                }
            },
        )
    }
}
