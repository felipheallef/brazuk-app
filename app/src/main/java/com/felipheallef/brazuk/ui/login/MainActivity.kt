package com.felipheallef.brazuk.ui.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.ContextMenu
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.felipheallef.brazuk.R
import com.felipheallef.brazuk.data.model.User
import com.felipheallef.brazuk.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        // set the default theme
        setTheme(R.style.Theme_Brazuk)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)

        binding.topAppBar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        val header = binding.navigationView.getHeaderView(0)
        val txtTitle = header.findViewById<TextView>(R.id.txt_drawer_header)
        val txtSubtitle = header.findViewById<TextView>(R.id.txt_drawer_subheader)

        sharedPref = getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )

        val loggedUser = sharedPref.getString("loggedUser", "0")

        if(loggedUser != "0") {
            val user = Gson().fromJson(loggedUser, User::class.java)
            txtTitle.text = user.displayName
            txtSubtitle.text = "@${user.username}"
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

}