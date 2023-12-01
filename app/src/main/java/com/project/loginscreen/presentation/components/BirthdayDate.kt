package com.project.loginscreen.presentation.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.loginscreen.R
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayDate() {
    var showDataDialog by remember { mutableStateOf(false) }
    var selectedDate  by remember { mutableStateOf("") }
    val datePickerState = rememberDatePickerState()
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    if (showDataDialog) {
        DatePickerDialog(
            onDismissRequest = { showDataDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        datePickerState
                            .selectedDateMillis?.let { millis ->
                                selectedDate = millis.toBrazilianDateFormat()

                            }
                            showDataDialog = false
                    }
                ) {
                    Text(text = "Escolher data")
                }
            }) {
            DatePicker(state = datePickerState)
        }
    }

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = selectedDate,
                onValueChange = {
                    val input = if (it.isBlank()) {
                        val alert = Toast.makeText(context, "Este campo não pode ser vazio", Toast.LENGTH_SHORT)
                    } else {

                    }
                },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(3.dp))
                    .border(BorderStroke(0.8.dp, Color.Gray))
                    .height(55.dp)
                    .fillMaxWidth()
                    .onFocusChanged {
                        if (it.isFocused) {
                            showDataDialog = true
                            focusManager.clearFocus(force = true)
                        }
                    }
                ,
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
                label = {
                    Text("Data de nascimento")
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "DateButton",
                    )
                },
                readOnly = true
            )
        }
    }
}
fun Long.toBrazilianDateFormat(
    pattern: String = "dd/MM/yyyy"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}

@Composable
fun BirthdayText(
    modifier: Modifier,
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Data de nascimento",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 19.sp
            )
        }
        Spacer(modifier = Modifier.padding(2.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Não será exibida publicamente.",
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                fontSize = 13.sp
            )
        }
    }
}

