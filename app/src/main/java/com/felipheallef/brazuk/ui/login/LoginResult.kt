package com.felipheallef.brazuk.ui.login

import com.felipheallef.brazuk.data.model.User

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
        val success: User? = null,
        val error: String? = null
)