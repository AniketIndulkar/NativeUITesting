package com.example.nativeuitesting.espresso.matchers

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

/**
 * Color-related matchers.
 */
object ColorMatchers {

    @JvmStatic
    fun withBackgroundColor(@ColorInt expectedColor: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with background color: $expectedColor")
            }

            override fun matchesSafely(view: View): Boolean {
                val drawable = view.background ?: return false
                if (drawable is ColorDrawable) {
                    return drawable.color == expectedColor
                }
                return false
            }
        }
    }

    @JvmStatic
    fun withBackgroundColor(colorHex: String): Matcher<View> {
        val color = android.graphics.Color.parseColor(colorHex)
        return withBackgroundColor(color)
    }

    @JvmStatic
    fun withTextColor(@ColorInt expectedColor: Int): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with text color: $expectedColor")
            }

            override fun matchesSafely(textView: TextView): Boolean {
                return textView.currentTextColor == expectedColor
            }
        }
    }

    @JvmStatic
    fun withTextColor(colorHex: String): Matcher<View> {
        val color = android.graphics.Color.parseColor(colorHex)
        return withTextColor(color)
    }

    @JvmStatic
    fun withHintTextColor(@ColorInt expectedColor: Int): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with hint text color: $expectedColor")
            }

            override fun matchesSafely(textView: TextView): Boolean {
                return textView.currentHintTextColor == expectedColor
            }
        }
    }
}