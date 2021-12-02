package com.tik.bfaagithubusers.ui.detail

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tik.bfaagithubusers.R
import com.tik.bfaagithubusers.R.drawable.ic_baseline_favorite_24
import com.tik.bfaagithubusers.R.drawable.ic_round_favorite_border_24
import com.tik.bfaagithubusers.database.User
import com.tik.bfaagithubusers.utils.SectionsPagerAdapter
import com.tik.bfaagithubusers.databinding.ActivityDetailUserBinding
import com.tik.bfaagithubusers.helper.ViewModelFactory
import com.tik.bfaagithubusers.model.DetailUser
import com.tik.bfaagithubusers.ui.favorite.FavoriteActivity
import com.tik.bfaagithubusers.utils.SettingPreferences

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var myString: String

    private lateinit var users: User
    private var isFav: Boolean = false
    private var detailUser: DetailUser? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val userName = intent.getStringExtra(EXTRA_USERNAME)
        myString = userName.toString()

        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        detailViewModel = ViewModelProvider(
//            this,
//            ViewModelProvider.NewInstanceFactory()
//        ).get(DetailViewModel::class.java)

        detailViewModel = obtainViewModel(this)
        detailViewModel.detailUser.observe(this, { users ->
            setData(users)
        })

        detailViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        userName?.let {
            detailViewModel.getDetailUser(it)

        }

        detailViewModel.getAllNotes().observe(this, { noteList ->
            if (noteList != null) {
                val found = noteList.any  {
                    it.login == userName
                }
                if (found){
                    isFav = true
                    binding.favAdd.setImageResource(ic_baseline_favorite_24)
                }
            }
        })

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

        binding.imgUser.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun favAction(detailUser: DetailUser) {
        Log.e(TAG, "addFav: clicked")
        users = User()
        users.let { users ->
            users.id = detailUser.id
            users.login = detailUser.login
            users.name = detailUser.name
            users.location = detailUser.location
            users.publicRepos = detailUser.publicRepos
            users.company = detailUser.company
            users.followers = detailUser.followers
            users.following = detailUser.following
            users.avatarUrl = detailUser.avatarUrl
        }
        users.let {
            if (isFav) {
                detailViewModel.delete(it)
                showToast("Deleted Fav")
                isFav = false
                binding.favAdd.setImageResource(ic_round_favorite_border_24)
            } else {
                detailViewModel.insert(it)
                showToast("Added Fav")
                isFav = true
                binding.favAdd.setImageResource(ic_baseline_favorite_24)
            }
        }
    }

    private fun setData(user: DetailUser) {
        binding.tvName.text = user.name
        binding.tvUsername.text = user.login
        binding.tvLocation.text = user.location
        binding.tvFollowers.text = user.followers.toString()
        binding.tvFollowing.text = user.following.toString()
        binding.tvRepository.text = user.publicRepos.toString()
        Glide.with(this)
            .load(user.avatarUrl)
            .into(binding.imgUser)
        binding.favAdd.setOnClickListener {
            favAction(user);
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.body.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
    }

    fun getMyData(): String {
        return myString
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        val pref = SettingPreferences.getInstance(dataStore)
        val factory = ViewModelFactory.getInstance(activity.application, pref)
        return ViewModelProvider(activity, factory).get(DetailViewModel::class.java)
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")