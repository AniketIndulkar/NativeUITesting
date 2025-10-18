package com.example.nativeuitesting.espresso.screens

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.nativeuitesting.espresso.actions.ClickActions
import com.example.nativeuitesting.espresso.actions.TextActions
import com.example.nativeuitesting.espresso.actions.WaitActions
import com.example.nativeuitesting.espresso.assertions.ViewAssertions
import com.example.nativeuitesting.espresso.config.TimeoutConfig

/**
 * Base class for all Screen/Robot classes.
 * Provides common functionality for screen objects.
 */
abstract class BaseScreen {

    /**
     * Override this to specify the root view ID of the screen.
     * Used for verification that the screen is displayed.
     */
    protected open val rootViewId: Int? = null

    /**
     * Override this to specify a unique text/title on the screen.
     * Used for verification that the screen is displayed.
     */
    protected open val screenTitle: String? = null

    /**
     * Verifies that this screen is currently displayed.
     * Override this method for custom verification logic.
     */
    open fun isDisplayed(): BaseScreen {
        rootViewId?.let {
            WaitActions.waitForView(it, TimeoutConfig.DEFAULT_WAIT_TIMEOUT_MS)
            ViewAssertions.assertDisplayed(it)
        }

        screenTitle?.let {
            WaitActions.waitForView(it, TimeoutConfig.DEFAULT_WAIT_TIMEOUT_MS)
            onView(withText(it)).check(matches(ViewMatchers.isDisplayed()))
        }

        return this
    }

    /**
     * Waits for this screen to be displayed.
     */
    open fun waitForScreen(timeoutMs: Long = TimeoutConfig.DEFAULT_WAIT_TIMEOUT_MS): BaseScreen {
        rootViewId?.let {
            WaitActions.waitForView(it, timeoutMs)
        }
        return this
    }

    // ============================================
    // HELPER METHODS (Protected - for subclasses)
    // ============================================

    protected fun clickOn(@IdRes viewId: Int) {
        ClickActions.clickOn(viewId)
    }

    protected fun clickOn(text: String) {
        ClickActions.clickOn(text)
    }

    protected fun typeText(@IdRes viewId: Int, text: String) {
        TextActions.writeTo(viewId, text)
    }

    protected fun clearText(@IdRes viewId: Int) {
        TextActions.clearText(viewId)
    }

    protected fun waitForView(@IdRes viewId: Int, timeoutMs: Long = TimeoutConfig.DEFAULT_WAIT_TIMEOUT_MS) {
        WaitActions.waitForView(viewId, timeoutMs)
    }

    protected fun waitUntilGone(@IdRes viewId: Int, timeoutMs: Long = TimeoutConfig.DEFAULT_WAIT_TIMEOUT_MS) {
        WaitActions.waitUntilGone(viewId, timeoutMs)
    }

    protected fun assertDisplayed(@IdRes viewId: Int) {
        ViewAssertions.assertDisplayed(viewId)
    }

    protected fun assertText(@IdRes viewId: Int, expectedText: String) {
        onView(withId(viewId)).check(matches(withText(expectedText)))
    }

    protected fun getView(@IdRes viewId: Int): ViewInteraction {
        return onView(withId(viewId))
    }

    protected fun sleep(millis: Long) {
        Thread.sleep(millis)
    }
}