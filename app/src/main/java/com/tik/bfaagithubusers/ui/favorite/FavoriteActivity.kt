package com.tik.bfaagithubusers.ui.favorite

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tik.bfaagithubusers.R
import com.tik.bfaagithubusers.adapter.ListFavUsersAdapter
import com.tik.bfaagithubusers.databinding.ActivityDetailUserBinding
import com.tik.bfaagithubusers.databinding.ActivityFavoriteBinding
import com.tik.bfaagithubusers.helper.ViewModelFactory
import com.tik.bfaagithubusers.ui.detail.DetailViewModel
import com.tik.bfaagithubusers.utils.SettingPreferences

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    private lateinit var adapter: ListFavUsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val favoriteViewModel = obtainViewModel(this@FavoriteActivity)
        favoriteViewModel.getAllNotes().observe(this, { noteList ->
            if (noteList != null) {
                adapter.setListNotes(noteList)
            }
        })

        adapter = ListFavUsersAdapter(this@FavoriteActivity)

        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.setHasFixedSize(true)
        binding.rvNotes.adapter = adapter

//        binding.fabAdd?.setOnClickListener { view ->
//            if (view.id == R.id.fab_add) {
//                val intent = Intent(this@MainActivity, NoteAddUpdateActivity::class.java)
//                startActivity(intent)
//            }
//        }
    }


//    private fun obtainViewModel(activity: AppCompatActivity): FavoriteViewModel {
//        val factory = ViewModelFactory.getInstance(activity.application)
//        return ViewModelProvider(activity, factory).get(FavoriteViewModel::class.java)
//    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteViewModel {
        val pref = SettingPreferences.getInstance(dataStore)
        val factory = ViewModelFactory.getInstance(activity.application, pref)
        return ViewModelProvider(activity, factory).get(FavoriteViewModel::class.java)
    }
}


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")