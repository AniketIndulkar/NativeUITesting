package com.example.nativeuitesting.espresso.matchers

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

/**
 * Hierarchy-related matchers.
 */
object HierarchyMatchers {

    @JvmStatic
    fun withChildCount(count: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with child count: $count")
            }

            override fun matchesSafely(view: View): Boolean {
                if (view !is ViewGroup) return false
                return view.childCount == count
            }
        }
    }

    @JvmStatic
    fun withMinChildCount(minCount: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with minimum child count: $minCount")
            }

            override fun matchesSafely(view: View): Boolean {
                if (view !is ViewGroup) return false
                return view.childCount >= minCount
            }
        }
    }

    @JvmStatic
    fun isNthChild(position: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("is $position-th child")
            }

            override fun matchesSafely(view: View): Boolean {
                val parent = view.parent as? ViewGroup ?: return false
                return parent.indexOfChild(view) == position
            }
        }
    }

    @JvmStatic
    fun isFirstChild(): Matcher<View> = isNthChild(0)

    @JvmStatic
    fun isLastChild(): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("is last child")
            }

            override fun matchesSafely(view: View): Boolean {
                val parent = view.parent as? ViewGroup ?: return false
                return parent.indexOfChild(view) == parent.childCount - 1
            }
        }
    }

    @JvmStatic
    fun atIndex(matcher: Matcher<View>, index: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            private var currentIndex = 0
            override fun describeTo(description: Description) {
                description.appendText("matcher at index $index: ")
                matcher.describeTo(description)
            }

            override fun matchesSafely(view: View): Boolean {
                if (matcher.matches(view)) {
                    if (currentIndex == index) {
                        return true
                    }
                    currentIndex++
                }
                return false
            }
        }
    }
}