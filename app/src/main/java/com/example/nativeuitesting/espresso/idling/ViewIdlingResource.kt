package com.example.nativeuitesting.espresso.idling

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingResource
import org.hamcrest.Matcher

/**
 * Waits for a view to appear and be displayed.
 */
class ViewIdlingResource(
    private val viewMatcher: Matcher<View>,
    private val timeoutMs: Long
) : IdlingResource {

    private var resourceCallback: IdlingResource.ResourceCallback? = null
    private val startTime = System.currentTimeMillis()

    override fun getName(): String = "ViewIdlingResource for $viewMatcher"

    override fun isIdleNow(): Boolean {
        val elapsed = System.currentTimeMillis() - startTime

        if (elapsed >= timeoutMs) {
            resourceCallback?.onTransitionToIdle()
            return true
        }

        var found = false
        try {
            onView(viewMatcher).check { view, noViewFoundException ->
                found = (noViewFoundException == null && view != null && view.isShown)
            }
        } catch (e: Exception) {
            found = false
        }

        if (found) {
            resourceCallback?.onTransitionToIdle()
            return true
        }

        return false
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.resourceCallback = callback
    }
}