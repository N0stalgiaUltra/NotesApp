package com.n0stalgiaultra.notesapp

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.n0stalgiaultra.notesapp.presentation.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Test
    fun testLaunchMainActivity(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()))
    }

    @Test
    fun testEmptyNotes(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.emptyNotesText))
            .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)))
    }

    @Test
    fun testNavCreateNoteActivity() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.fab_addNote)).perform(click())

        onView(withId(R.id.newNoteActivity)).check(matches(isDisplayed()))
    }

    @Test
    fun testNavBackToMainActivity() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.fab_addNote)).perform(click())
        onView(withId(R.id.newNoteActivity)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()))
    }

    @Test
    fun testClickInNote() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.mainRecView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.openNoteActivity)).check(matches(isDisplayed()))

    }
}