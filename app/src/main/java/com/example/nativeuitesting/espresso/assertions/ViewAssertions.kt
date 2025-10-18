package com.example.nativeuitesting.espresso.assertions

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not

/**
 * View state assertions for UI testing.
 *
 * Usage:
 * ```
 * import com.mobile.core.uitesting.espresso.assertions.ViewAssertions.*
 *
 * assertDisplayed(R.id.button)
 * assertEnabled(R.id.submit)
 * assertChecked(R.id.checkbox)
 * ```
 */
object ViewAssertions {

    @JvmStatic
    fun assertDisplayed(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(isDisplayed()))
    }

    @JvmStatic
    fun assertDisplayed(text: String) {
        onView(withText(text)).check(matches(isDisplayed()))
    }

    @JvmStatic
    fun assertDisplayed(matcher: Matcher<View>) {
        onView(matcher).check(matches(isDisplayed()))
    }

    @JvmStatic
    fun assertNotDisplayed(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(not(isDisplayed())))
    }

    @JvmStatic
    fun assertNotExist(@IdRes viewId: Int) {
        onView(withId(viewId)).check(doesNotExist())
    }

    @JvmStatic
    fun assertEnabled(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(isEnabled()))
    }

    @JvmStatic
    fun assertDisabled(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(not(isEnabled())))
    }

    @JvmStatic
    fun assertClickable(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(isClickable()))
    }

    @JvmStatic
    fun assertNotClickable(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(not(isClickable())))
    }

    @JvmStatic
    fun assertChecked(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(isChecked()))
    }

    @JvmStatic
    fun assertNotChecked(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(not(isChecked())))
    }

    @JvmStatic
    fun assertSelected(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(isSelected()))
    }

    @JvmStatic
    fun assertFocused(@IdRes viewId: Int) {
        onView(withId(viewId)).check(matches(hasFocus()))
    }
}