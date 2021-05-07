package com.felipheallef.brazuk.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.felipheallef.brazuk.data.LoginRepository
import com.felipheallef.brazuk.data.Result

import com.felipheallef.brazuk.R
import com.felipheallef.brazuk.api.ApiResponse
import com.felipheallef.brazuk.data.LoginDataSource
import com.felipheallef.brazuk.data.model.User

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<ApiResponse<User>>()
    val loginResult: LiveData<ApiResponse<User>> = _loginResult

    fun login(username: String, password: String) {

        LoginDataSource().login(username, password) { response ->

            try {
                _loginResult.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}