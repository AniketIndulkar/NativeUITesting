package com.example.nativeuitesting.espresso.assertions


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.google.android.material.R

/**
 * Snackbar assertions.
 */
object SnackbarAssertions {

    @JvmStatic
    fun assertSnackbarDisplayed() {
        onView(withId(R.id.snackbar_text))
            .check(matches(isDisplayed()))
    }

    @JvmStatic
    fun assertSnackbarText(text: String) {
        onView(withId(R.id.snackbar_text))
            .check(matches(withText(text)))
    }

    @JvmStatic
    fun assertSnackbarWithAction(actionText: String) {
        onView(withId(R.id.snackbar_action))
            .check(matches(withText(actionText)))
    }
}