package com.example.nativeuitesting.espresso.matchers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * RecyclerView-specific matchers.
 */
object RecyclerMatchers {

    @JvmStatic
    fun withItemCount(count: Int): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with item count: $count")
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                return recyclerView.adapter?.itemCount == count
            }
        }
    }

    @JvmStatic
    fun withMinItemCount(minCount: Int): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with minimum item count: $minCount")
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                return (recyclerView.adapter?.itemCount ?: 0) >= minCount
            }
        }
    }

    @JvmStatic
    fun isEmpty(): Matcher<View> = withItemCount(0)

    @JvmStatic
    fun isNotEmpty(): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("is not empty")
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                return (recyclerView.adapter?.itemCount ?: 0) > 0
            }
        }
    }

    @JvmStatic
    fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                    ?: return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }
}