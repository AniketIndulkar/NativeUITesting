package com.example.nativeuitesting.espresso.actions


import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingPolicies
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.nativeuitesting.espresso.idling.IdlingResourceManager
import com.example.nativeuitesting.espresso.idling.ViewGoneIdlingResource
import com.example.nativeuitesting.espresso.idling.ViewIdlingResource
import com.example.nativeuitesting.espresso.idling.ViewStateIdlingResource
import org.hamcrest.Matcher
import java.util.concurrent.TimeUnit

/**
 * Wait actions for handling async operations.
 *
 * Usage:
 * ```
 * import com.mobile.core.uitesting.espresso.actions.WaitActions.*
 *
 * waitForView(R.id.loading)
 * waitUntilDisplayed(R.id.result)
 * waitUntilGone(R.id.progress)
 * ```
 */
object WaitActions {

    private const val DEFAULT_TIMEOUT_MS = 5000L

    init {
        IdlingPolicies.setMasterPolicyTimeout(10, TimeUnit.SECONDS)
        IdlingPolicies.setIdlingResourceTimeout(10, TimeUnit.SECONDS)
    }

    @JvmStatic
    @JvmOverloads
    fun waitForView(@IdRes viewId: Int, timeoutMs: Long = DEFAULT_TIMEOUT_MS) {
        waitForView(withId(viewId), timeoutMs)
    }

    @JvmStatic
    @JvmOverloads
    fun waitForView(text: String, timeoutMs: Long = DEFAULT_TIMEOUT_MS) {
        waitForView(withText(text), timeoutMs)
    }

    @JvmStatic
    @JvmOverloads
    fun waitForView(matcher: Matcher<View>, timeoutMs: Long = DEFAULT_TIMEOUT_MS) {
        val idlingResource = ViewIdlingResource(matcher, timeoutMs)
        IdlingResourceManager.withResource(idlingResource) {
            onView(matcher).check(matches(isDisplayed()))
        }
    }

    @JvmStatic
    @JvmOverloads
    fun waitUntilDisplayed(@IdRes viewId: Int, timeoutMs: Long = DEFAULT_TIMEOUT_MS) {
        waitForView(viewId, timeoutMs)
    }

    @JvmStatic
    @JvmOverloads
    fun waitUntilDisplayed(text: String, timeoutMs: Long = DEFAULT_TIMEOUT_MS) {
        waitForView(text, timeoutMs)
    }

    @JvmStatic
    @JvmOverloads
    fun waitUntilDisplayed(matcher: Matcher<View>, timeoutMs: Long = DEFAULT_TIMEOUT_MS) {
        waitForView(matcher, timeoutMs)
    }

    @JvmStatic
    @JvmOverloads
    fun waitUntilGone(@IdRes viewId: Int, timeoutMs: Long = DEFAULT_TIMEOUT_MS) {
        waitUntilGone(withId(viewId), timeoutMs)
    }

    @JvmStatic
    @JvmOverloads
    fun waitUntilGone(matcher: Matcher<View>, timeoutMs: Long = DEFAULT_TIMEOUT_MS) {
        val idlingResource = ViewGoneIdlingResource(matcher, timeoutMs)
        IdlingResourceManager.withResource(idlingResource) {
            onView(isRoot()).check { _, _ -> }
        }
    }

    @JvmStatic
    @JvmOverloads
    fun waitUntilEnabled(@IdRes viewId: Int, timeoutMs: Long = DEFAULT_TIMEOUT_MS) {
        val idlingResource = ViewStateIdlingResource(withId(viewId), isEnabled(), timeoutMs)
        IdlingResourceManager.withResource(idlingResource) {
            onView(withId(viewId)).check(matches(isEnabled()))
        }
    }

    @JvmStatic
    @JvmOverloads
    fun waitUntilClickable(@IdRes viewId: Int, timeoutMs: Long = DEFAULT_TIMEOUT_MS) {
        val idlingResource = ViewStateIdlingResource(withId(viewId), isClickable(), timeoutMs)
        IdlingResourceManager.withResource(idlingResource) {
            onView(withId(viewId)).check(matches(isClickable()))
        }
    }

    @JvmStatic
    fun wait(millis: Long) {
        try {
            Thread.sleep(millis)
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
            throw RuntimeException("Wait interrupted", e)
        }
    }

    @JvmStatic
    fun <T> withIdlingResource(resource: IdlingResource, block: () -> T): T {
        return IdlingResourceManager.withResource(resource, block)
    }
}