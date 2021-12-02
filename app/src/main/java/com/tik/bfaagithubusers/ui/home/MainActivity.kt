package com.tik.bfaagithubusers.ui.home

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tik.bfaagithubusers.adapter.ListUsersAdapter
import com.tik.bfaagithubusers.R
import com.tik.bfaagithubusers.databinding.ActivityMainBinding
import com.tik.bfaagithubusers.helper.ViewModelFactory
import com.tik.bfaagithubusers.model.Items
import com.tik.bfaagithubusers.ui.detail.DetailUserActivity
import com.tik.bfaagithubusers.ui.detail.DetailViewModel
import com.tik.bfaagithubusers.ui.favorite.FavoriteActivity
import com.tik.bfaagithubusers.ui.setting.SettingActivity
import com.tik.bfaagithubusers.ui.setting.SettingViewModel
import com.tik.bfaagithubusers.utils.SettingPreferences

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var settingViewModel: SettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)


        settingViewModel = obtainViewModel(this)

        settingViewModel.getThemeSettings().observe(this,
            { isDarkModeActive: Boolean ->
                if (isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            })

        val layoutManager = LinearLayoutManager(this)
        binding.rvUsers.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUsers.addItemDecoration(itemDecoration)

        mainViewModel.listUsers.observe(this, { users ->
            setData(users)
        })

        mainViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        mainViewModel.searchUser("sidiq")

    }


    private fun setData(users: List<Items>) {

        val adapter = ListUsersAdapter(users, this@MainActivity)

        adapter.setOnItemClickCallback(object : ListUsersAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Items) {
                showSelectedHero(data)
            }
        })
        binding.rvUsers.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mainViewModel.searchUser(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.fav -> moveFav(FavoriteActivity::class.java)
            R.id.setting -> moveSetting(SettingActivity::class.java)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun moveSetting(java: Class<SettingActivity>) {
        val intent = Intent(this@MainActivity, java)
        startActivity(intent)
    }

    private fun moveFav(java: Class<FavoriteActivity>) {
        val intent = Intent(this@MainActivity, java)
        startActivity(intent)
    }


    private fun showSelectedHero(user: Items) {
        val moveWithDataIntent = Intent(this@MainActivity, DetailUserActivity::class.java)
        moveWithDataIntent.putExtra(DetailUserActivity.EXTRA_USERNAME, user.login)
        startActivity(moveWithDataIntent)
    }


    private fun obtainViewModel(activity: AppCompatActivity): SettingViewModel {
        val pref = SettingPreferences.getInstance(dataStore)
        val factory = ViewModelFactory.getInstance(activity.application, pref)
        return ViewModelProvider(activity, factory).get(SettingViewModel::class.java)
    }
}