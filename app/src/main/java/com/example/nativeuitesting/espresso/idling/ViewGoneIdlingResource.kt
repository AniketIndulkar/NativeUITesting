package com.example.nativeuitesting.espresso.idling

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingResource
import org.hamcrest.Matcher

/**
 * Waits for a view to disappear (gone or invisible).
 */
class ViewGoneIdlingResource(
    private val viewMatcher: Matcher<View>,
    private val timeoutMs: Long
) : IdlingResource {

    private var resourceCallback: IdlingResource.ResourceCallback? = null
    private val startTime = System.currentTimeMillis()

    override fun getName(): String = "ViewGoneIdlingResource for $viewMatcher"

    override fun isIdleNow(): Boolean {
        val elapsed = System.currentTimeMillis() - startTime

        if (elapsed >= timeoutMs) {
            resourceCallback?.onTransitionToIdle()
            return true
        }

        var isGone = false
        try {
            onView(viewMatcher).check { view, noViewFoundException ->
                isGone = (noViewFoundException != null || view == null || !view.isShown)
            }
        } catch (e: Exception) {
            isGone = true
        }

        if (isGone) {
            resourceCallback?.onTransitionToIdle()
            return true
        }

        return false
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.resourceCallback = callback
    }
}