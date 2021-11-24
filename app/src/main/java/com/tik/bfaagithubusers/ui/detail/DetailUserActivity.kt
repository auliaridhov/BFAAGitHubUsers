package com.tik.bfaagithubusers.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tik.bfaagithubusers.R
import com.tik.bfaagithubusers.utils.SectionsPagerAdapter
import com.tik.bfaagithubusers.databinding.ActivityDetailUserBinding
import com.tik.bfaagithubusers.model.DetailUser

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var myString: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val userName = intent.getStringExtra(EXTRA_USERNAME)
        myString = userName.toString()

        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(DetailViewModel::class.java)
        detailViewModel.detailUser.observe(this, { users ->
            setData(users)
        })

        detailViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        userName?.let { detailViewModel.getDetailUser(it) }

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

    private  fun  setData(user: DetailUser){
        binding.tvName.text = user.name
        binding.tvUsername.text = user.login
        binding.tvLocation.text = user.location
        binding.tvFollowers.text = user.followers.toString()
        binding.tvFollowing.text = user.following.toString()
        binding.tvRepository.text = user.publicRepos.toString()
        Glide.with(this)
            .load(user.avatarUrl)
            .into(binding.imgUser)
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
}