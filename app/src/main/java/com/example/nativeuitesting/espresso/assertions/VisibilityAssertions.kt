package com.example.nativeuitesting.espresso.assertions

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not

/**
 * Visibility-specific assertions.
 */
object VisibilityAssertions {

    @JvmStatic
    fun assertVisible(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @JvmStatic
    fun assertInvisible(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(withEffectiveVisibility(Visibility.INVISIBLE)))
    }

    @JvmStatic
    fun assertGone(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @JvmStatic
    fun assertCompletelyDisplayed(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(isCompletelyDisplayed()))
    }

    @JvmStatic
    fun assertPartiallyDisplayed(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(isDisplayingAtLeast(50)))
    }
}