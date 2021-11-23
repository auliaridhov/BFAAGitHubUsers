package com.tik.bfaagithubusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.tik.bfaagithubusers.databinding.ActivityDetailUserBinding
import com.tik.bfaagithubusers.databinding.ActivityMainBinding
import de.hdodenhof.circleimageview.CircleImageView

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val userName = intent.getStringExtra(EXTRA_USERNAME)

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
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.body.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
    }
}