package com.project.loginscreen.presentation.user.use_cases

data class UserUseCases(
    val addUser: AddUser,
    val showUser : ShowUser,
    val editUser: EditUser,
    val compareUser : CompareUser,
    val comparePass : ComparePass,
    val compareEmail : CompareEmail
)
