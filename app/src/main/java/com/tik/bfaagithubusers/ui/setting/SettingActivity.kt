package com.tik.bfaagithubusers.ui.setting

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.switchmaterial.SwitchMaterial
import com.tik.bfaagithubusers.R
import com.tik.bfaagithubusers.helper.ViewModelFactory
import com.tik.bfaagithubusers.ui.detail.DetailViewModel
import com.tik.bfaagithubusers.utils.SettingPreferences


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


class SettingActivity : AppCompatActivity() {

    private lateinit var settingViewModel: SettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme)

        settingViewModel = obtainViewModel(this)

        settingViewModel.getThemeSettings().observe(this,
            { isDarkModeActive: Boolean ->
                if (isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    switchTheme.isChecked = true
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    switchTheme.isChecked = false
                }
            })

        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            settingViewModel.saveThemeSetting(isChecked)
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): SettingViewModel {
        val pref = SettingPreferences.getInstance(dataStore)
        val factory = ViewModelFactory.getInstance(activity.application, pref)
        return ViewModelProvider(activity, factory).get(SettingViewModel::class.java)
    }
}
