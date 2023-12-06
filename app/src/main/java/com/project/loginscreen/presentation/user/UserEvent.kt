package com.project.loginscreen.presentation.user

sealed class UserEvent {
    data class EnteredName(val value : String): UserEvent()
    data class EnteredEmail(val value : String): UserEvent()
    data class EnteredPassword(val value : String): UserEvent()
    data class EnteredBirthday(val value : String): UserEvent()
    object SaveUser: UserEvent()
}