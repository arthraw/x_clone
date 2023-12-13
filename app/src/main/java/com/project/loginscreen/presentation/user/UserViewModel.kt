package com.project.loginscreen.presentation.user

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.project.loginscreen.data.model.entities.UserEntity
import com.project.loginscreen.presentation.user.use_cases.ListUsers
import com.project.loginscreen.presentation.user.use_cases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val useCases: UserUseCases
) : ViewModel() {

    private val _userName = mutableStateOf(TextFieldValue(""))
    val userName = _userName

    private val _userEmail = mutableStateOf(TextFieldValue(""))
    val userEmail = _userEmail

    private val _userPassword = mutableStateOf(TextFieldValue(""))
    val userPassword = _userPassword

    private val _userBirthday = mutableStateOf(TextFieldValue(""))
    val userBirthday = _userBirthday

    fun onEvent(event: UserEvent) {
        when (event) {
            is UserEvent.EnteredName -> {
                _userName.value = userName.value.copy(
                    text = event.value
                )
            }

            is UserEvent.EnteredEmail -> {
                _userEmail.value = userEmail.value.copy(
                    text = event.value
                )
            }

            is UserEvent.EnteredPassword -> {
                _userPassword.value = userPassword.value.copy(
                    text = event.value
                )
            }

            is UserEvent.EnteredBirthday -> {
                _userBirthday.value = userBirthday.value.copy(
                    text = event.value
                )
                userBirthday.value.text.replace("/","")
            }

            is UserEvent.SaveUser -> {
                viewModelScope.launch {
                    try {
                        useCases.addUser(
                            UserEntity(
                                name = userName.value.text,
                                email = userEmail.value.text,
                                password = userPassword.value.text,
                                birthDate = userBirthday.value.text.toLong()
                            )
                        )
                    } catch (e: Exception) {
                        throw e
//                        throw InvalidUserException("ERROR: Error in user insert.") // Remover e definir um aviso para a UI
                    }
                }
            }

            else -> {}
        }
    }
    fun showAllUsers() {
        viewModelScope.launch {
            val users =  useCases.listUsers()
            for (user in users) {
                println("ID: ${user.userId}, User: ${user.name}, Email: ${user.email}, Password: ${user.password}, Birth Day: ${user.birthDate}")
            }
        }
    }

    fun searchName(name: String) {
        viewModelScope.launch {
            if (useCases.compareUser(name) != null) {
                throw Exception("Nome ja existe")
            }
        }
        return
    }

    fun searchUserExists(name: String) : String {
        viewModelScope.launch {
            if (useCases.compareUser(name) == null) {
                throw Exception("Usuario nao existe")
            }
        }
        return name
    }

    fun searchEmail(email: String) {
        viewModelScope.launch {
            if (useCases.compareEmail(email) != null) {
                println(useCases.compareEmail(email))
                throw Exception("Email ja existe")
            }
        }
        return
    }

    fun searchPass(name: String, password: String) {
        viewModelScope.launch {
            useCases.comparePass(name, password)
            if (userPassword.value.text != password) {
                Log.d("TESTE","senha ${userPassword.value.text} nao confere")
                throw Exception("Senha nao confere")
            }
        }
        return
    }
}