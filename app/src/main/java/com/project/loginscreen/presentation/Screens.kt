package com.project.loginscreen.presentation

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login")
    object SignUpScreen : Screen("signup")
    object PasswordScreen : Screen("passwordLogin")
    object Feed : Screen("feed")
    object Success : Screen("success")


    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
