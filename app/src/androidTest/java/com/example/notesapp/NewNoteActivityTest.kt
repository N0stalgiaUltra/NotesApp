package com.example.notesapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.notesapp.presentation.MainActivity
import com.example.notesapp.presentation.NewNoteActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class NewNoteActivityTest {

    @get: Rule
    val activityRule : ActivityScenarioRule<NewNoteActivity> =
        ActivityScenarioRule(NewNoteActivity::class.java)

    @Test
    fun testLaunchMainActivity(){
        onView(ViewMatchers.withId(R.id.newNoteActivity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testEditTextVisibility(){
        onView(ViewMatchers.withId(R.id.etNoteName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testRadioButtonsVisibility(){
        onView(ViewMatchers.withId(R.id.color_buttons_group))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


}