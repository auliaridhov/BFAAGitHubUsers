package com.tik.bfaagithubusers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvUsers: RecyclerView
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUsers = findViewById(R.id.rv_users)
        rvUsers.setHasFixedSize(true)
        list.addAll(listUsers)
        showRecyclerList()
    }

    private val listUsers: ArrayList<User>
        get() {
            val username = resources.getStringArray(R.array.username)
            val name = resources.getStringArray(R.array.name)
            val location = resources.getStringArray(R.array.location)
            val repository = resources.getStringArray(R.array.repository)
            val company = resources.getStringArray(R.array.company)
            val followers = resources.getStringArray(R.array.followers)
            val following = resources.getStringArray(R.array.following)
            val avatar = resources.obtainTypedArray(R.array.avatar)
            val listuser = ArrayList<User>()
            for (i in username.indices) {
                val user = User(
                    username[i],
                    name[i],
                    location[i],
                    repository[i].toInt(),
                    company[i],
                    followers[i].toInt(),
                    following[i].toInt(),
                    avatar.getResourceId(i, -1)
                )
                listuser.add(user)
            }
            return listuser
        }

    private fun showRecyclerList() {
        rvUsers.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUsersAdapter(list)
        rvUsers.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUsersAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                showSelectedHero(data)
            }
        })
    }


    private fun showSelectedHero(user: User) {
        val moveWithObjectIntent = Intent(this@MainActivity, DetailUserActivity::class.java)
        moveWithObjectIntent.putExtra(DetailUserActivity.EXTRA_USER, user)
        startActivity(moveWithObjectIntent)
    }
}