package com.example.nativeuitesting.espresso.assertions

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*

/**
 * State-specific assertions (checked, selected, focused).
 */
object StateAssertions {

    @JvmStatic
    fun assertChecked(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(isChecked()))
    }

    @JvmStatic
    fun assertNotChecked(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(isNotChecked()))
    }

    @JvmStatic
    fun assertSelected(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(isSelected()))
    }

    @JvmStatic
    fun assertNotSelected(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(org.hamcrest.Matchers.not(isSelected())))
    }

    @JvmStatic
    fun assertFocused(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(hasFocus()))
    }

    @JvmStatic
    fun assertNotFocused(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(org.hamcrest.Matchers.not(hasFocus())))
    }
}