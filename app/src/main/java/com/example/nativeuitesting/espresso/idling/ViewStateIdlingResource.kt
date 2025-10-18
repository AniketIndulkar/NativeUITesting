package com.example.nativeuitesting.espresso.idling

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingResource
import org.hamcrest.Matcher

/**
 * Waits for a view to match a specific state.
 */
class ViewStateIdlingResource(
    private val viewMatcher: Matcher<View>,
    private val stateMatcher: Matcher<View>,
    private val timeoutMs: Long
) : IdlingResource {

    private var resourceCallback: IdlingResource.ResourceCallback? = null
    private val startTime = System.currentTimeMillis()

    override fun getName(): String = "ViewStateIdlingResource for $viewMatcher with state $stateMatcher"

    override fun isIdleNow(): Boolean {
        val elapsed = System.currentTimeMillis() - startTime

        if (elapsed >= timeoutMs) {
            resourceCallback?.onTransitionToIdle()
            return true
        }

        var stateMatches = false
        try {
            Espresso.onView(viewMatcher).check { view, noViewFoundException ->
                if (noViewFoundException == null && view != null) {
                    stateMatches = stateMatcher.matches(view)
                }
            }
        } catch (e: Exception) {
            stateMatches = false
        }

        if (stateMatches) {
            resourceCallback?.onTransitionToIdle()
            return true
        }

        return false
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.resourceCallback = callback
    }
}