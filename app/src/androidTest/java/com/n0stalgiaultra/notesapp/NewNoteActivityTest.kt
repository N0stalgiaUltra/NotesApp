package com.n0stalgiaultra.notesapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.n0stalgiaultra.notesapp.presentation.NewNoteActivity
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

    @Test
    fun testCreateNote() {
        val testText = "Test text"

        onView(withId(R.id.etNoteName))
            .perform(replaceText(testText))
            .check(matches(withText(testText)))

        onView(withId(R.id.rbGreen))
            .perform(click())
            .check(matches(isChecked()))

        onView(withId(R.id.btnCreateNote))
            .perform(click())

    }
}