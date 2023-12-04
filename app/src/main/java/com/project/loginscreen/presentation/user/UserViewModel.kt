package com.project.loginscreen.presentation.user

import androidx.lifecycle.ViewModel
import com.project.loginscreen.presentation.user.use_cases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val UseruseCases: UserUseCases
): ViewModel() {


}