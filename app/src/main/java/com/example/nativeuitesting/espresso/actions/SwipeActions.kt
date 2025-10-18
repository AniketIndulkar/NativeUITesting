package com.example.nativeuitesting.espresso.actions

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId

/**
 * Swipe actions for UI testing.
 */
object SwipeActions {

    @JvmStatic
    fun swipeUp(@IdRes viewId: Int) {
        onView(withId(viewId)).perform(ViewActions.swipeUp())
    }

    @JvmStatic
    fun swipeDown(@IdRes viewId: Int) {
        onView(withId(viewId)).perform(ViewActions.swipeDown())
    }

    @JvmStatic
    fun swipeLeft(@IdRes viewId: Int) {
        onView(withId(viewId)).perform(ViewActions.swipeLeft())
    }

    @JvmStatic
    fun swipeRight(@IdRes viewId: Int) {
        onView(withId(viewId)).perform(ViewActions.swipeRight())
    }
}