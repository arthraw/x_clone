package com.project.loginscreen.presentation.user

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.loginscreen.data.model.entities.InvalidUserException
import com.project.loginscreen.data.model.entities.UserEntity
import com.project.loginscreen.presentation.user.use_cases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
    fun onEvent(event: UserEvent)  {
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
                    } catch (e: Exception) {
                        throw e
//                        throw InvalidUserException("ERROR: Error in user insert.") // Remover e definir um aviso para a UI
                    }
                }
            }

        }
    }

    fun searchName(name : String) {
        viewModelScope.launch {
            if (name == useCases.compareUser(name)) {
                throw InvalidUserException("User already exists.")
            }
        }
        return
    }

    fun searchEmail(email : String) {
        viewModelScope.launch {
            if (email == useCases.compareEmail(email)) {
                throw InvalidUserException("Email already exists in another account.")
            }
        }
        return
    }

    fun searchPass(password : String) {
        viewModelScope.launch {
            if (password == useCases.comparePass(userPassword.value.text)) {
                throw InvalidUserException("User already exists.")
            }
        }
        return
    }
}