package com.felipheallef.brazuk.ui.login.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.felipheallef.brazuk.R
import com.felipheallef.brazuk.data.model.User
import com.felipheallef.brazuk.databinding.ActivityLoginBinding
import com.felipheallef.brazuk.ui.login.LoginViewModel
import com.felipheallef.brazuk.ui.login.LoginViewModelFactory
import com.felipheallef.brazuk.ui.activity.MainActivity
import com.google.gson.Gson

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Brazuk)
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )

        val loggedUser = sharedPref.getString("loggedUser", "0")

        if(loggedUser != "0") {
            val user = Gson().fromJson(loggedUser, User::class.java)
            updateUiWithUser(user)
        }

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
                .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            binding.btnLogin.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                binding.textinputEmail.error = getString(loginState.usernameError)
            } else {
                binding.textinputEmail.error = ""
            }
            if (loginState.passwordError != null) {
                binding.textinputPassword.error = getString(loginState.passwordError)
            } else {
                binding.textinputPassword.error = ""
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            binding.layoutLoading.visibility = View.GONE

            if (loginResult.success) {
                updateUiWithUser(loginResult.data!!)
            } else {
                showLoginFailed(loginResult.error!!.message)
            }

            setResult(Activity.RESULT_OK)

        })

        binding.username.doAfterTextChanged {
            loginViewModel.loginDataChanged(
                binding.username.text.toString(),
                binding.password.text.toString()
            )
        }

        binding.password.apply {
            doAfterTextChanged {
                loginViewModel.loginDataChanged(
                    binding.username.text.toString(),
                    binding.password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            binding.username.text.toString(),
                            binding.password.text.toString()
                        )
                }
                false
            }

            binding.btnLogin.setOnClickListener {
                binding.layoutLoading.visibility = View.VISIBLE
                loginViewModel.login(binding.username.text.toString(), binding.password.text.toString())
            }
        }
    }

    // completes login once authentication is successful
    private fun updateUiWithUser(model: User) {
        val prefsEditor = sharedPref.edit()
        val json = Gson().toJson(model)

        prefsEditor.putString("loggedUser", json)
        prefsEditor.apply()

        Intent(applicationContext, MainActivity::class.java).apply {
            startActivity(this)
            finish()
        }

    }

    private fun showLoginFailed(errorString: String) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_LONG).show()
    }
}