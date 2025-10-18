package com.example.nativeuitesting.espresso.actions

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matcher

/**
 * Scroll actions for UI testing.
 *
 * Usage:
 * ```
 * import com.mobile.core.uitesting.espresso.actions.ScrollActions.*
 *
 * scrollTo(R.id.bottom_view)
 * scrollTo("Terms and Conditions")
 * ```
 */
object ScrollActions {

    @JvmStatic
    fun scrollTo(@IdRes viewId: Int) {
        onView(withId(viewId)).perform(ViewActions.scrollTo())
    }

    @JvmStatic
    fun scrollTo(text: String) {
        onView(withText(text)).perform(ViewActions.scrollTo())
    }

    @JvmStatic
    fun scrollTo(matcher: Matcher<View>) {
        onView(matcher).perform(ViewActions.scrollTo())
    }
}