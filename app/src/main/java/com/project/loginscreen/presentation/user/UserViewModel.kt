package com.project.loginscreen.presentation.user

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.project.loginscreen.data.model.entities.InvalidUserException
import com.project.loginscreen.data.model.entities.UserEntity
import com.project.loginscreen.presentation.user.use_cases.ListUsers
import com.project.loginscreen.presentation.user.use_cases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
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

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

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
                                birthDate = userBirthday.value.text.toLongOrNull()
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveUser)
                    } catch (e: InvalidUserException) {
                        _eventFlow.emit(
                            UiEvent.ShowMessage(
                                message = e.message ?: "Erro ao tentar criar a conta."
                            )
                        )
                    }
                }
            }
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
            val user = useCases.compareUser(name)
            if (name.equals(user?.name, ignoreCase = true)) {
                _eventFlow.emit(
                    UiEvent.ShowMessage(
                        message = "Este nome já está em uso, tente outro.",
                        key = true
                    )
                )
            } else {
                _eventFlow.emit(UiEvent.SaveUser)
            }
        }
    }

    fun searchUserExists(name: String) {
        viewModelScope.launch {
            val user = useCases.compareUser(name)
            if (user == null) {
                _eventFlow.emit(
                    UiEvent.ShowMessage(
                        message = "Usuário não existe",
                        key = true
                    )
                )
            } else {
                _eventFlow.emit(UiEvent.UserExists(name))
            }
        }
    }

    fun searchEmail(email: String) {
        viewModelScope.launch {
            val user = useCases.compareEmail(email)
            if (email.equals(user?.email, ignoreCase = true)) {
                _eventFlow.emit(
                    UiEvent.ShowMessage(
                        message = "Este e-mail já está em uso em outra conta, tente outro.",
                        key = true
                    )
                )
            } else {
                _eventFlow.emit(UiEvent.SaveUser)
            }
        }
    }

    fun searchPass(name: String, password: String) {
        viewModelScope.launch {
            val user = useCases.comparePass(name,password)
            if (password != user?.password) {
                _eventFlow.emit(
                    UiEvent.ShowMessage(
                        message = "Senha errada, tente novamente.",
                        key = true
                    )
                )
            } else {
                _eventFlow.emit(UiEvent.SaveUser)
            }
        }
    }

    sealed class UiEvent {
        data class ShowMessage(val message: String, val key: Boolean = false) : UiEvent()
        data class UserExists(val userName: String) : UiEvent()
        object SaveUser: UiEvent()

    }
}