package com.tik.bfaagithubusers.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tik.bfaagithubusers.R
import com.tik.bfaagithubusers.database.User
import com.tik.bfaagithubusers.databinding.ItemRowUserBinding
import com.tik.bfaagithubusers.helper.UserDiffCallback
import com.tik.bfaagithubusers.model.Items
import com.tik.bfaagithubusers.ui.detail.DetailUserActivity
import de.hdodenhof.circleimageview.CircleImageView

class ListFavUsersAdapter(private val ctx: Context): RecyclerView.Adapter<ListFavUsersAdapter.UserViewHolder>() {
    private val listUser = ArrayList<User>()
    fun setListNotes(listNotes: List<User>) {
        val diffCallback = UserDiffCallback(this.listUser, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listUser.clear()
        this.listUser.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUser[position])
    }
    override fun getItemCount(): Int {
        return listUser.size
    }
    inner class UserViewHolder(private val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            with(binding) {
                tvItemName.text = user.login
                tvItemLocation.text = user.id.toString()
                Glide.with(ctx)
                    .load(user.avatarUrl)
                    .into(imgItemPhoto)
                cvRowUserFav.setOnClickListener {
                    val intent = Intent(it.context, DetailUserActivity::class.java)
                    intent.putExtra(DetailUserActivity.EXTRA_USERNAME, user.login)
                    it.context.startActivity(intent)
                }
            }
        }
    }
}