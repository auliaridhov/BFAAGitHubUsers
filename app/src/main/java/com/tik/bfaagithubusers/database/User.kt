package com.tik.bfaagithubusers.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class User(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = 0,

    @ColumnInfo(name = "login")
    var  login: String? = null,

    @ColumnInfo(name = "name")
    var  name: String? = null,

    @ColumnInfo(name = "location")
    var  location: String? = null,

    @ColumnInfo(name = "publicRepos")
    var  publicRepos: Int? = 0,

    @ColumnInfo(name = "company")
    var  company: String? = null,

    @ColumnInfo(name = "followers")
    var  followers: Int? = 0,

    @ColumnInfo(name = "following")
    var  following: Int? = 0,

    @ColumnInfo(name = "avatarUrl")
    var  avatarUrl: String? = null,

) : Parcelable
