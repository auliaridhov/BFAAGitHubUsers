package com.tik.bfaagithubusers.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.tik.bfaagithubusers.R
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class MainActivityTest{
    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun assertGetCircumference() {
        onView(withId(R.id.rv_users)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
//        onView(withId(R.id.edt_width)).perform(typeText(dummyWidth), closeSoftKeyboard())
//        onView(withId(R.id.edt_height)).perform(typeText(dummyHeight), closeSoftKeyboard())
//        onView(withId(R.id.btn_save)).check(matches(isDisplayed()))
//        onView(withId(R.id.btn_save)).perform(click())
//        onView(withId(R.id.btn_calculate_circumference)).check(matches(isDisplayed()))
//        onView(withId(R.id.btn_calculate_circumference)).perform(click())
//        onView(withId(R.id.tv_result)).check(matches(isDisplayed()))
//        onView(withId(R.id.tv_result)).check(matches(withText(dummyCircumference)))
    }
}