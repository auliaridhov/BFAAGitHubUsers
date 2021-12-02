package com.tik.bfaagithubusers.helper

import androidx.recyclerview.widget.DiffUtil
import com.tik.bfaagithubusers.database.User

class UserDiffCallback (private val mOldNoteList: List<User>, private val mNewNoteList: List<User>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldNoteList.size
    }
    override fun getNewListSize(): Int {
        return mNewNoteList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNoteList[oldItemPosition].id == mNewNoteList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldNoteList[oldItemPosition]
        val newEmployee = mNewNoteList[newItemPosition]
        return oldEmployee.login == newEmployee.login && oldEmployee.avatarUrl == newEmployee.avatarUrl
    }
}