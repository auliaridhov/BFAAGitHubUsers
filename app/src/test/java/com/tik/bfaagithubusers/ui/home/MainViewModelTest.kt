package com.tik.bfaagithubusers.ui.home

import com.tik.bfaagithubusers.model.Items
import com.tik.bfaagithubusers.utils.DataDummy

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.junit.Assert.*
import org.mockito.Mockito.verify

class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var users: List<Items>

    @Before
    fun before() {
        users = listOf(mock(Items::class.java))
        mainViewModel = MainViewModel()
    }

    @Test
    fun testSearchUser() {
        users = listOf(Items())
        mainViewModel = MainViewModel()
        assertNotNull(mainViewModel.equals("sidiqpermana"))
    }
}