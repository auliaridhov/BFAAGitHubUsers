package com.tik.bfaagithubusers.ui.following

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
import com.tik.bfaagithubusers.databinding.FragmentFollowingBinding
import com.tik.bfaagithubusers.model.Items
import com.tik.bfaagithubusers.ui.detail.DetailUserActivity

class FollowingFragment : Fragment() {

    private lateinit var fragmentFollowingBinding: FragmentFollowingBinding
    private lateinit var followingViewModel: FollowingViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentFollowingBinding = FragmentFollowingBinding.inflate(layoutInflater, container, false)
        return fragmentFollowingBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val activity: DetailUserActivity = activity as DetailUserActivity
            val myDataFromActivity: String = activity.getMyData()

            followingViewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(FollowingViewModel::class.java)

            val layoutManager = LinearLayoutManager(context)
            fragmentFollowingBinding.rvUsers.layoutManager = layoutManager
            val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
            fragmentFollowingBinding.rvUsers.addItemDecoration(itemDecoration)

            followingViewModel.listUsers.observe(viewLifecycleOwner, { users ->
                context?.let { setData(users, it) }
            })

            followingViewModel.isLoading.observe(viewLifecycleOwner, {
                showLoading(it)
            })

            followingViewModel.getFollowing(myDataFromActivity)

        }
    }

    private fun setData(users: List<Items>, ctx: Context) {

        val adapter = ListUsersAdapter(users, ctx)
        adapter.setOnItemClickCallback(object : ListUsersAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Items) {
                showSelectedHero(data, ctx)
            }
        })
        fragmentFollowingBinding.rvUsers.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        fragmentFollowingBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showSelectedHero(user: Items, ctx: Context) {
        Toast.makeText(ctx, user.login, Toast.LENGTH_SHORT).show()
    }

}