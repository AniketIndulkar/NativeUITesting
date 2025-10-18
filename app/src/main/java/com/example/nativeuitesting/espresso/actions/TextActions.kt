package com.example.nativeuitesting.espresso.actions

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId

/**
 * Text input actions for UI testing.
 *
 * Usage:
 * ```
 * import com.mobile.core.uitesting.espresso.actions.TextActions.*
 *
 * writeTo(R.id.username, "test@example.com")
 * typeTo(R.id.search, "Android")
 * clearText(R.id.password)
 * ```
 */
object TextActions {

    /**
     * Replaces text in a view and closes the keyboard.
     * Use this when you want to set text (not append).
     */
    @JvmStatic
    fun writeTo(@IdRes viewId: Int, text: String) {
        onView(withId(viewId)).perform(
            ViewActions.replaceText(text),
            ViewActions.closeSoftKeyboard()
        )
    }

    /**
     * Types text into a view (appends to existing text) and closes the keyboard.
     */
    @JvmStatic
    fun typeTo(@IdRes viewId: Int, text: String) {
        onView(withId(viewId)).perform(
            ViewActions.typeText(text),
            ViewActions.closeSoftKeyboard()
        )
    }

    /**
     * Clears all text from a view
     */
    @JvmStatic
    fun clearText(@IdRes viewId: Int) {
        onView(withId(viewId)).perform(ViewActions.clearText())
    }

    /**
     * Replaces text without closing the keyboard
     */
    @JvmStatic
    fun writeToWithoutClosingKeyboard(@IdRes viewId: Int, text: String) {
        onView(withId(viewId)).perform(ViewActions.replaceText(text))
    }

    /**
     * Types text without closing the keyboard
     */
    @JvmStatic
    fun typeToWithoutClosingKeyboard(@IdRes viewId: Int, text: String) {
        onView(withId(viewId)).perform(ViewActions.typeText(text))
    }

    /**
     * Closes the soft keyboard
     */
    @JvmStatic
    fun closeKeyboard() {
        onView(withId(android.R.id.content)).perform(ViewActions.closeSoftKeyboard())
    }

    /**
     * Presses the IME action button (Done, Next, Search, etc.)
     */
    @JvmStatic
    fun pressImeAction() {
        onView(withId(android.R.id.content)).perform(ViewActions.pressImeActionButton())
    }
}