package com.example.nativeuitesting.espresso.actions

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matcher


/**
 * RecyclerView-specific actions.
 *
 * Usage:
 * ```
 * import com.mobile.core.uitesting.espresso.actions.specialized.RecyclerActions.*
 *
 * clickItemAt(R.id.recycler, 2)
 * clickItemWithText(R.id.recycler, "Item 5")
 * scrollToPosition(R.id.recycler, 10)
 * ```
 */
object RecyclerViewActions {  // ← Different name, no conflict!

    /**
     * Clicks on a RecyclerView item at the given position
     */
    @JvmStatic
    fun clickItemAt(@IdRes recyclerId: Int, position: Int) {
        onView(withId(recyclerId)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                ViewActions.click()
            )
        )
    }

    /**
     * Clicks on a RecyclerView item that contains the given text
     */
    @JvmStatic
    fun clickItemWithText(@IdRes recyclerId: Int, text: String) {
        onView(withId(recyclerId)).perform(
            RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                hasDescendant(withText(text)),
                ViewActions.click()
            )
        )
    }

    /**
     * Clicks on a RecyclerView item matching the given matcher
     */
    @JvmStatic
    fun clickItem(@IdRes recyclerId: Int, itemMatcher: Matcher<View>) {
        onView(withId(recyclerId)).perform(
            RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                itemMatcher,
                ViewActions.click()
            )
        )
    }

    /**
     * Scrolls RecyclerView to the given position
     */
    @JvmStatic
    fun scrollToPosition(@IdRes recyclerId: Int, position: Int) {
        onView(withId(recyclerId)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position)
        )
    }

    /**
     * Scrolls RecyclerView to an item with the given text
     */
    @JvmStatic
    fun scrollToItemWithText(@IdRes recyclerId: Int, text: String) {
        onView(withId(recyclerId)).perform(
            RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                hasDescendant(withText(text))
            )
        )
    }

    /**
     * Scrolls RecyclerView to an item matching the given matcher
     */
    @JvmStatic
    fun scrollToItem(@IdRes recyclerId: Int, itemMatcher: Matcher<View>) {
        onView(withId(recyclerId)).perform(
            RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(itemMatcher)
        )
    }

    /**
     * Clicks on a child view within a RecyclerView item
     */
    @JvmStatic
    fun clickItemChild(@IdRes recyclerId: Int, position: Int, @IdRes childId: Int) {
        onView(withId(recyclerId)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                clickChildViewWithId(childId)
            )
        )
    }

    /**
     * Performs a custom action on an item at the given position
     */
    @JvmStatic
    fun performActionOnItemAt(
        @IdRes recyclerId: Int,
        position: Int,
        action: androidx.test.espresso.ViewAction
    ) {
        onView(withId(recyclerId)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                action
            )
        )
    }

    /**
     * Performs a custom action on an item matching the given matcher
     */
    @JvmStatic
    fun performActionOnItem(
        @IdRes recyclerId: Int,
        itemMatcher: Matcher<View>,
        action: androidx.test.espresso.ViewAction
    ) {
        onView(withId(recyclerId)).perform(
            RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                itemMatcher,
                action
            )
        )
    }

    private fun clickChildViewWithId(@IdRes childId: Int) = ViewActions.actionWithAssertions(
        object : androidx.test.espresso.ViewAction {
            override fun getConstraints() = isDisplayed()

            override fun getDescription() = "Click on child view with id $childId"

            override fun perform(uiController: androidx.test.espresso.UiController, view: View) {
                val childView = view.findViewById<View>(childId)
                    ?: throw IllegalStateException("Child view with id $childId not found")
                childView.performClick()
            }
        }
    )
}