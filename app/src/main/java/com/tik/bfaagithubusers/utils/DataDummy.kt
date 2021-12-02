package com.tik.bfaagithubusers.utils

import com.tik.bfaagithubusers.model.Items

object DataDummy {
    fun generateDummySearchUser(): List<Items> {
        val users = ArrayList<Items>()

        users.add(
            Items(
                "sidiqpermana",
                4090245,
                "MDQ6VXNlcjQwOTAyNDU=",
                "https://avatars.githubusercontent.com/u/4090245?v=4",
                "",
                "https://api.github.com/users/sidiqpermana",
                "https://github.com/sidiqpermana",
                "https://api.github.com/users/sidiqpermana/followers",
                "https://api.github.com/users/sidiqpermana/following{/other_user}",
                "https://api.github.com/users/sidiqpermana/gists{/gist_id}",
                "https://api.github.com/users/sidiqpermana/starred{/owner}{/repo}",
                "https://api.github.com/users/sidiqpermana/subscriptions",
                "https://api.github.com/users/sidiqpermana/orgs",
                "https://api.github.com/users/sidiqpermana/repos",
                "https://api.github.com/users/sidiqpermana/events{/privacy}",
                "https://api.github.com/users/sidiqpermana/received_events",
                "User",
                false,
                )
        )
        users.add(
            Items(
                "sidiqpermana22",
                18606835,
                "MDQ6VXNlcjE4NjA2ODM1",
                "https://avatars.githubusercontent.com/u/18606835?v=4",
                "",
                "https://api.github.com/users/sidiqpermana22",
                "https://github.com/sidiqpermana22",
                "https://api.github.com/users/sidiqpermana22/followers",
                "https://api.github.com/users/sidiqpermana22/following{/other_user}",
                "https://api.github.com/users/sidiqpermana22/gists{/gist_id}",
                "https://api.github.com/users/sidiqpermana22/starred{/owner}{/repo}",
                "https://api.github.com/users/sidiqpermana22/subscriptions",
                "https://api.github.com/users/sidiqpermana22/orgs",
                "https://api.github.com/users/sidiqpermana22/repos",
                "https://api.github.com/users/sidiqpermana22/events{/privacy}",
                "https://api.github.com/users/sidiqpermana22/received_events",
                "User",
                false,
                )
        )

        return users
    }
}