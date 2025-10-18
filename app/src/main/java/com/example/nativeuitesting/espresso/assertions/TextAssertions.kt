package com.example.nativeuitesting.espresso.assertions

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.containsString

/**
 * Text-related assertions for UI testing.
 */
object TextAssertions {

    @JvmStatic
    fun assertText(@IdRes viewId: Int, expectedText: String) {
        onView(withId(viewId)).check(matches(withText(expectedText)))
    }

    @JvmStatic
    fun assertTextContains(@IdRes viewId: Int, substring: String) {
        onView(withId(viewId)).check(matches(withText(containsString(substring))))
    }

    @JvmStatic
    fun assertHint(@IdRes viewId: Int, expectedHint: String) {
        onView(withId(viewId)).check(matches(withHint(expectedHint)))
    }

    @JvmStatic
    fun assertContentDescription(@IdRes viewId: Int, description: String) {
        onView(withId(viewId)).check(matches(withContentDescription(description)))
    }
}