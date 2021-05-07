package com.felipheallef.brazuk.ui.fragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.felipheallef.brazuk.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main_preferences, rootKey)
    }
}
