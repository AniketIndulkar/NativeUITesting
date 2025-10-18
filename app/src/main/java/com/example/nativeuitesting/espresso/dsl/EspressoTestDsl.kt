package com.example.nativeuitesting.espresso.dsl
// core/uitesting/espresso/dsl/EspressoTestDsl.kt

import androidx.annotation.IdRes
import com.example.nativeuitesting.espresso.actions.ClickActions
import com.example.nativeuitesting.espresso.actions.GeneralActions
import com.example.nativeuitesting.espresso.actions.RecyclerViewActions
import com.example.nativeuitesting.espresso.actions.ScrollActions
import com.example.nativeuitesting.espresso.actions.TextActions
import com.example.nativeuitesting.espresso.actions.WaitActions
import com.example.nativeuitesting.espresso.assertions.RecyclerViewAssertions
import com.example.nativeuitesting.espresso.assertions.TextAssertions
import com.example.nativeuitesting.espresso.assertions.ViewAssertions
import com.example.nativeuitesting.espresso.config.TimeoutConfig

/**
 * DSL marker to prevent nested DSL scopes
 */
@DslMarker
annotation class EspressoTestDslMarker

/**
 * Base DSL context that provides clean access to framework actions and assertions.
 * Robot classes extend this to get DSL-style syntax.
 *
 * This is a minimal starting set - add more functions as you need them!
 */
@EspressoTestDslMarker
abstract class EspressoTestContext {

    // ============================================
    // CLICK ACTIONS
    // ============================================

    /**
     * Click on a view by resource ID
     */
    protected fun clickOn(@IdRes viewId: Int) {
        ClickActions.clickOn(viewId)
    }

    /**
     * Click on a view by text
     */
    protected fun clickOn(text: String) {
        ClickActions.clickOn(text)
    }

    /**
     * Long click on a view by resource ID
     */
    protected fun longClickOn(@IdRes viewId: Int) {
        ClickActions.longClickOn(viewId)
    }

    /**
     * Double click on a view by resource ID
     */
    protected fun doubleClickOn(@IdRes viewId: Int) {
        ClickActions.doubleClickOn(viewId)
    }

    // ============================================
    // TEXT ACTIONS
    // ============================================

    /**
     * Replace text in a view
     */
    protected fun writeTo(@IdRes viewId: Int, text: String) {
        TextActions.writeTo(viewId, text)
    }

    /**
     * Type text into a view (appends to existing text)
     */
    protected fun typeTo(@IdRes viewId: Int, text: String) {
        TextActions.typeTo(viewId, text)
    }

    /**
     * Clear text from a view
     */
    protected fun clearText(@IdRes viewId: Int) {
        TextActions.clearText(viewId)
    }

    /**
     * Close the soft keyboard
     */
    protected fun closeKeyboard() {
        TextActions.closeKeyboard()
    }

    // ============================================
    // SCROLL ACTIONS
    // ============================================

    /**
     * Scroll to a view by resource ID
     */
    protected fun scrollTo(@IdRes viewId: Int) {
        ScrollActions.scrollTo(viewId)
    }

    /**
     * Scroll to a view by text
     */
    protected fun scrollTo(text: String) {
        ScrollActions.scrollTo(text)
    }

    // ============================================
    // WAIT ACTIONS (Critical for stable tests!)
    // ============================================

    /**
     * Wait for a view to be displayed (by ID)
     */
    protected fun waitForView(
        @IdRes viewId: Int,
        timeoutMs: Long = TimeoutConfig.DEFAULT_WAIT_TIMEOUT_MS
    ) {
        WaitActions.waitForView(viewId, timeoutMs)
    }

    /**
     * Wait for a view to be displayed (by text)
     */
    protected fun waitForView(
        text: String,
        timeoutMs: Long = TimeoutConfig.DEFAULT_WAIT_TIMEOUT_MS
    ) {
        WaitActions.waitForView(text, timeoutMs)
    }

    /**
     * Wait until a view is displayed
     */
    protected fun waitUntilDisplayed(
        @IdRes viewId: Int,
        timeoutMs: Long = TimeoutConfig.DEFAULT_WAIT_TIMEOUT_MS
    ) {
        WaitActions.waitUntilDisplayed(viewId, timeoutMs)
    }

    /**
     * Wait until a view is gone/hidden
     */
    protected fun waitUntilGone(
        @IdRes viewId: Int,
        timeoutMs: Long = TimeoutConfig.DEFAULT_WAIT_TIMEOUT_MS
    ) {
        WaitActions.waitUntilGone(viewId, timeoutMs)
    }

    // ============================================
    // RECYCLERVIEW ACTIONS
    // ============================================

    /**
     * Click on a RecyclerView item at position
     */
    protected fun clickRecyclerItemAt(@IdRes recyclerId: Int, position: Int) {
        RecyclerViewActions.clickItemAt(recyclerId, position)
    }

    /**
     * Click on a RecyclerView item by text
     */
    protected fun clickRecyclerItemWithText(@IdRes recyclerId: Int, text: String) {
        RecyclerViewActions.clickItemWithText(recyclerId, text)
    }

    /**
     * Scroll RecyclerView to position
     */
    protected fun scrollRecyclerToPosition(@IdRes recyclerId: Int, position: Int) {
        RecyclerViewActions.scrollToPosition(recyclerId, position)
    }

    /**
     * Scroll RecyclerView to item with text
     */
    protected fun scrollRecyclerToItemWithText(@IdRes recyclerId: Int, text: String) {
        RecyclerViewActions.scrollToItemWithText(recyclerId, text)
    }

    // ============================================
    // VIEW ASSERTIONS
    // ============================================

    /**
     * Assert view is displayed (by ID)
     */
    protected fun assertDisplayed(@IdRes viewId: Int) {
        ViewAssertions.assertDisplayed(viewId)
    }

    /**
     * Assert view is displayed (by text)
     */
    protected fun assertDisplayed(text: String) {
        ViewAssertions.assertDisplayed(text)
    }

    /**
     * Assert view is not displayed
     */
    protected fun assertNotDisplayed(@IdRes viewId: Int) {
        ViewAssertions.assertNotDisplayed(viewId)
    }

    /**
     * Assert view does not exist
     */
    protected fun assertNotExist(@IdRes viewId: Int) {
        ViewAssertions.assertNotExist(viewId)
    }

    /**
     * Assert view is enabled
     */
    protected fun assertEnabled(@IdRes viewId: Int) {
        ViewAssertions.assertEnabled(viewId)
    }

    /**
     * Assert view is disabled
     */
    protected fun assertDisabled(@IdRes viewId: Int) {
        ViewAssertions.assertDisabled(viewId)
    }

    /**
     * Assert view is clickable
     */
    protected fun assertClickable(@IdRes viewId: Int) {
        ViewAssertions.assertClickable(viewId)
    }

    // ============================================
    // TEXT ASSERTIONS
    // ============================================

    /**
     * Assert text matches exactly
     */
    protected fun assertText(@IdRes viewId: Int, expectedText: String) {
        TextAssertions.assertText(viewId, expectedText)
    }

    /**
     * Assert text contains substring
     */
    protected fun assertTextContains(@IdRes viewId: Int, substring: String) {
        TextAssertions.assertTextContains(viewId, substring)
    }

    /**
     * Assert hint text
     */
    protected fun assertHint(@IdRes viewId: Int, expectedHint: String) {
        TextAssertions.assertHint(viewId, expectedHint)
    }

    // ============================================
    // RECYCLERVIEW ASSERTIONS
    // ============================================

    /**
     * Assert RecyclerView has specific item count
     */
    protected fun assertRecyclerItemCount(@IdRes recyclerId: Int, expectedCount: Int) {
        RecyclerViewAssertions.assertRecyclerItemCount(recyclerId, expectedCount)
    }

    /**
     * Assert RecyclerView is not empty
     */
    protected fun assertRecyclerNotEmpty(@IdRes recyclerId: Int) {
        RecyclerViewAssertions.assertRecyclerNotEmpty(recyclerId)
    }

    /**
     * Assert RecyclerView is empty
     */
    protected fun assertRecyclerEmpty(@IdRes recyclerId: Int) {
        RecyclerViewAssertions.assertRecyclerEmpty(recyclerId)
    }

    // ============================================
    // GENERAL ACTIONS
    // ============================================

    /**
     * Press the back button
     */
    protected fun pressBack() {
        GeneralActions.pressBack()
    }

    // ============================================
    // UTILITY FUNCTIONS
    // ============================================

    /**
     * Sleep for specified milliseconds (use sparingly!)
     */
    protected fun sleep(millis: Long) {
        Thread.sleep(millis)
    }
}