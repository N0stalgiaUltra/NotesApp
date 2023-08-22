package com.example.notesapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.notesapp.presentation.NewNoteActivity
import com.example.notesapp.presentation.OpenNoteActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class OpenNoteActivityTest {

    @get: Rule
    val activityRule : ActivityScenarioRule<OpenNoteActivity> =
        ActivityScenarioRule(OpenNoteActivity::class.java)

    @Test
    fun testLaunchMainActivity(){
        Espresso.onView(ViewMatchers.withId(R.id.openNoteActivity))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}