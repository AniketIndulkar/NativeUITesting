package com.example.nativeuitesting.espresso.matchers

import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

/**
 * Complex compound matchers.
 */
object CompoundMatchers {

    /**
     * Matches if ANY of the matchers match
     */
    @JvmStatic
    fun anyOf(vararg matchers: Matcher<View>): Matcher<View> {
        return org.hamcrest.Matchers.anyOf(matchers.toList())
    }

    /**
     * Matches if ALL of the matchers match
     */
    @JvmStatic
    fun allOf(vararg matchers: Matcher<View>): Matcher<View> {
        return org.hamcrest.Matchers.allOf(matchers.toList())
    }

    /**
     * Negates a matcher
     */
    @JvmStatic
    fun not(matcher: Matcher<View>): Matcher<View> {
        return org.hamcrest.Matchers.not(matcher)
    }

    /**
     * Matches the first view that matches the given matcher
     */
    @JvmStatic
    fun first(matcher: Matcher<View>): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            private var isFirstMatch = true

            override fun describeTo(description: Description) {
                description.appendText("first matching: ")
                matcher.describeTo(description)
            }

            override fun matchesSafely(view: View): Boolean {
                if (matcher.matches(view) && isFirstMatch) {
                    isFirstMatch = false
                    return true
                }
                return false
            }
        }
    }
}