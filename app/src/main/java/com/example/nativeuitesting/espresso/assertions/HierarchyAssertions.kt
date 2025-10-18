package com.example.nativeuitesting.espresso.assertions

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matcher

/**
 * Hierarchy-related assertions (parent, child, sibling).
 */
object HierarchyAssertions {

    @JvmStatic
    fun assertHasDescendant(@IdRes viewId: Int, descendantMatcher: Matcher<View>) {
        onView(withId(viewId)).check(matches(hasDescendant(descendantMatcher)))
    }

    @JvmStatic
    fun assertHasDescendantWithText(@IdRes viewId: Int, text: String) {
        onView(withId(viewId)).check(matches(hasDescendant(withText(text))))
    }

    @JvmStatic
    fun assertHasDescendantWithId(@IdRes viewId: Int, @IdRes descendantId: Int) {
        onView(withId(viewId)).check(matches(hasDescendant(withId(descendantId))))
    }

    @JvmStatic
    fun assertHasSibling(@IdRes viewId: Int, siblingMatcher: Matcher<View>) {
        onView(withId(viewId)).check(matches(hasSibling(siblingMatcher)))
    }

    @JvmStatic
    fun assertHasSiblingWithText(@IdRes viewId: Int, text: String) {
        onView(withId(viewId)).check(matches(hasSibling(withText(text))))
    }

    @JvmStatic
    fun assertChildCount(@IdRes viewId: Int, expectedCount: Int) {
        onView(withId(viewId)).check { view, _ ->
            val actualCount = (view as? android.view.ViewGroup)?.childCount ?: 0
            if (actualCount != expectedCount) {
                throw AssertionError("Expected $expectedCount children, but found $actualCount")
            }
        }
    }
}