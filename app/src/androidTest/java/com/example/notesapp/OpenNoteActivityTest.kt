package com.example.notesapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
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
        onView(withId(R.id.openNoteActivity))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testEditText() {
        val newText = "New Test Text"
        onView(withId(R.id.noteEditText)).perform(replaceText(newText))
        onView(withId(R.id.noteEditText)).check(matches(withText(newText)))
    }

}