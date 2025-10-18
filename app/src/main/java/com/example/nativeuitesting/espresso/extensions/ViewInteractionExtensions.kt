package com.example.nativeuitesting.espresso.extensions

import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers

/**
 * Extension functions for ViewInteraction.
 */

// Perform multiple actions in sequence
fun ViewInteraction.performAll(vararg actions: ViewAction): ViewInteraction {
    actions.forEach { perform(it) }
    return this
}

// Check if displayed (shorthand)
fun ViewInteraction.isDisplayed(): ViewInteraction {
    return check(matches(ViewMatchers.isDisplayed()))
}

// Perform action and check assertion (fluent)
fun ViewInteraction.performAndCheck(
    action: ViewAction,
    assertion: ViewAssertion
): ViewInteraction {
    perform(action)
    check(assertion)
    return this
}

// Repeat action N times
fun ViewInteraction.repeat(times: Int, action: ViewAction): ViewInteraction {
    repeat(times) {
        perform(action)
    }
    return this
}

// Perform with delay
fun ViewInteraction.performWithDelay(action: ViewAction, delayMs: Long): ViewInteraction {
    Thread.sleep(delayMs)
    perform(action)
    return this
}