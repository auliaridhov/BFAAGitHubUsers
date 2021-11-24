package com.tik.bfaagithubusers.ui.followers

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tik.bfaagithubusers.adapter.ListUsersAdapter
import com.tik.bfaagithubusers.databinding.FragmentFollowersBinding
import com.tik.bfaagithubusers.model.Items
import com.tik.bfaagithubusers.ui.detail.DetailUserActivity


class FollowersFragment : Fragment() {

    private lateinit var fragmentFollowersBinding: FragmentFollowersBinding
    private lateinit var followersViewModel: FollowersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentFollowersBinding = FragmentFollowersBinding.inflate(layoutInflater, container, false)
        return fragmentFollowersBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val activity: DetailUserActivity = activity as DetailUserActivity
            val myDataFromActivity: String = activity.getMyData()

            followersViewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(FollowersViewModel::class.java)

            val layoutManager = LinearLayoutManager(context)
            fragmentFollowersBinding.rvUsers.layoutManager = layoutManager
            val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
            fragmentFollowersBinding.rvUsers.addItemDecoration(itemDecoration)

            followersViewModel.listUsers.observe(viewLifecycleOwner, { users ->
                context?.let { setData(users, it) }
            })

            followersViewModel.isLoading.observe(viewLifecycleOwner, {
                showLoading(it)
            })

            followersViewModel.getFollowers(myDataFromActivity)

        }
    }

    private fun setData(users: List<Items>, ctx: Context) {

        val adapter = ListUsersAdapter(users, ctx)
        adapter.setOnItemClickCallback(object : ListUsersAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Items) {
                showSelectedHero(data, ctx)
            }
        })
        fragmentFollowersBinding.rvUsers.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        fragmentFollowersBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showSelectedHero(user: Items, ctx: Context) {
        Toast.makeText(ctx, user.login, Toast.LENGTH_SHORT).show()
    }

}