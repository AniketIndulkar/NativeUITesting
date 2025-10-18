package com.example.nativeuitesting.espresso.extensions

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

/**
 * General test extension functions.
 */

// Quick view by ID
fun viewById(@IdRes id: Int): ViewInteraction = onView(withId(id))

// Quick view by text
fun viewByText(text: String): ViewInteraction = onView(withText(text))

// Sleep shorthand
fun sleep(millis: Long) = Thread.sleep(millis)

// Retry with delay
inline fun <T> retryOnFailure(
    times: Int = 3,
    delayMs: Long = 1000,
    block: () -> T
): T {
    var lastException: Exception? = null

    repeat(times) { attempt ->
        try {
            return block()
        } catch (e: Exception) {
            lastException = e
            if (attempt < times - 1) {
                Thread.sleep(delayMs)
            }
        }
    }

    throw lastException ?: IllegalStateException("Retry failed with no exception")
}