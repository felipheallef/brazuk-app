package com.felipheallef.brazuk.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.felipheallef.brazuk.R
import com.felipheallef.brazuk.databinding.ActivitySettingsBinding
import com.felipheallef.brazuk.ui.fragment.SettingsFragment

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, SettingsFragment())
            .commit()

    }
}