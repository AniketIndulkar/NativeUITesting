package com.example.nativeuitesting.espresso.matchers

import android.view.View
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Text-specific matchers.
 */
object TextMatchers {

    @JvmStatic
    fun containsTextIgnoreCase(substring: String): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("contains text (case insensitive): $substring")
            }

            override fun matchesSafely(textView: TextView): Boolean {
                val text = textView.text?.toString() ?: return false
                return text.contains(substring, ignoreCase = true)
            }
        }
    }

    @JvmStatic
    fun startsWithText(prefix: String): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("starts with: $prefix")
            }

            override fun matchesSafely(textView: TextView): Boolean {
                return textView.text?.toString()?.startsWith(prefix) == true
            }
        }
    }

    @JvmStatic
    fun endsWithText(suffix: String): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("ends with: $suffix")
            }

            override fun matchesSafely(textView: TextView): Boolean {
                return textView.text?.toString()?.endsWith(suffix) == true
            }
        }
    }

    @JvmStatic
    fun matchesPattern(pattern: Regex): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("matches pattern: ${pattern.pattern}")
            }

            override fun matchesSafely(textView: TextView): Boolean {
                val text = textView.text?.toString() ?: return false
                return pattern.matches(text)
            }
        }
    }

    @JvmStatic
    fun withTextLength(length: Int): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("text length: $length")
            }

            override fun matchesSafely(textView: TextView): Boolean {
                return (textView.text?.length ?: 0) == length
            }
        }
    }

    @JvmStatic
    fun withMinTextLength(minLength: Int): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("minimum text length: $minLength")
            }

            override fun matchesSafely(textView: TextView): Boolean {
                return (textView.text?.length ?: 0) >= minLength
            }
        }
    }

    @JvmStatic
    fun withMaxTextLength(maxLength: Int): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("maximum text length: $maxLength")
            }

            override fun matchesSafely(textView: TextView): Boolean {
                return (textView.text?.length ?: 0) <= maxLength
            }
        }
    }
}