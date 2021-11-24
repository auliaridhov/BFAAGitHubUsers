package com.tik.bfaagithubusers.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tik.bfaagithubusers.R
import com.tik.bfaagithubusers.model.Items
import de.hdodenhof.circleimageview.CircleImageView

class ListUsersAdapter(private val listUser: List<Items>, private val ctx: Context) :
    RecyclerView.Adapter<ListUsersAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_user, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listUser[position]
//        holder.imgPhoto.setImageResource(user.avatar)
        Glide.with(ctx)
            .load(user.avatarUrl)
            .into(holder.imgPhoto)
        holder.tvName.text = user.login
        holder.tvLocation.text = user.id.toString()
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUser[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listUser.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: CircleImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvLocation: TextView = itemView.findViewById(R.id.tv_item_location)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Items)
    }
}