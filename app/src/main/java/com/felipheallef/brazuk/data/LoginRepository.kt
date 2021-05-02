package com.felipheallef.brazuk.data

import com.felipheallef.brazuk.api.service.AccountService
import com.felipheallef.brazuk.data.model.LoggedInUser
import com.felipheallef.brazuk.data.model.User

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: User? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String){
        // handle login
        dataSource.login(username, password) {
            if (it.status == 200) {
                setLoggedInUser(it.user)
            }
        }
    }

    fun setLoggedInUser(loggedInUser: User?) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore

    }

}