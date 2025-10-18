package com.example.nativeuitesting.espresso.assertions

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*

/**
 * Assertions specifically for dialog views.
 * Dialog views require .inRoot(isDialog()) to be found correctly.
 */
object DialogAssertions {

    /**
     * Assert dialog view is displayed
     */
    @JvmStatic
    fun assertDisplayed(@IdRes viewId: Int) {
        onView(withId(viewId))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
    }

    /**
     * Assert dialog view is GONE
     */
    @JvmStatic
    fun assertGone(@IdRes viewId: Int) {
        onView(withId(viewId))
            .inRoot(isDialog())
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    /**
     * Assert dialog view is enabled
     */
    @JvmStatic
    fun assertEnabled(@IdRes viewId: Int) {
        onView(withId(viewId))
            .inRoot(isDialog())
            .check(matches(isEnabled()))
    }

    /**
     * Assert dialog view has text
     */
    @JvmStatic
    fun assertText(@IdRes viewId: Int, expectedText: String) {
        onView(withId(viewId))
            .inRoot(isDialog())
            .check(matches(withText(expectedText)))
    }

    /**
     * Assert text is displayed in dialog
     */
    @JvmStatic
    fun assertTextDisplayed(text: String) {
        onView(withText(text))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
    }
}