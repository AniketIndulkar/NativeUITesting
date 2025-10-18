package com.example.nativeuitesting.espresso.extensions

import android.view.View
import org.hamcrest.Matcher
import org.hamcrest.Matchers

/**
 * Extension functions for Matchers.
 */

// Combine matchers with AND
infix fun Matcher<View>.and(other: Matcher<View>): Matcher<View> {
    return Matchers.allOf(this, other)
}

// Combine matchers with OR
infix fun Matcher<View>.or(other: Matcher<View>): Matcher<View> {
    return Matchers.anyOf(this, other)
}

// Negate matcher
operator fun Matcher<View>.not(): Matcher<View> {
    return Matchers.not(this)
}