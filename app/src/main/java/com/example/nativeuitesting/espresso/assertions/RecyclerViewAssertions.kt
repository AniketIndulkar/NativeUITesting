package com.example.nativeuitesting.espresso.assertions

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId

/**
 * RecyclerView-specific assertions.
 */
object RecyclerViewAssertions {

    @JvmStatic
    fun assertRecyclerItemCount(@IdRes recyclerId: Int, expectedCount: Int) {
        onView(withId(recyclerId)).check { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView
            val actualCount = recyclerView.adapter?.itemCount ?: 0

            if (actualCount != expectedCount) {
                throw AssertionError(
                    "RecyclerView item count mismatch. Expected: $expectedCount, Actual: $actualCount"
                )
            }
        }
    }

    @JvmStatic
    fun assertRecyclerNotEmpty(@IdRes recyclerId: Int) {
        onView(withId(recyclerId)).check { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView
            val itemCount = recyclerView.adapter?.itemCount ?: 0

            if (itemCount == 0) {
                throw AssertionError("RecyclerView is empty but should contain items")
            }
        }
    }

    @JvmStatic
    fun assertRecyclerEmpty(@IdRes recyclerId: Int) {
        assertRecyclerItemCount(recyclerId, 0)
    }
}