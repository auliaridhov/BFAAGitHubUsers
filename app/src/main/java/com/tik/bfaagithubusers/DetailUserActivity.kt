package com.tik.bfaagithubusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class DetailUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val tvUserName: TextView = findViewById(R.id.tvUsername)
        val tvName: TextView = findViewById(R.id.tvName)
        val tvLocation: TextView = findViewById(R.id.tvLocation)
        val tvFollowers: TextView = findViewById(R.id.tvFollowers)
        val tvFollowing: TextView = findViewById(R.id.tvFollowing)
        val tvRepository: TextView = findViewById(R.id.tvRepository)
        val imgUser: CircleImageView = findViewById(R.id.imgUser)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        tvUserName.text = user.username
        tvName.text = user.name
        tvLocation.text = user.location
        tvFollowers.text = user.followers.toString()
        tvFollowing.text = user.following.toString()
        tvRepository.text = user.repository.toString()

        imgUser.setImageResource(user.avatar)
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}