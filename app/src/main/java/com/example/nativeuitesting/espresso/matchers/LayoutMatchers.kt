package com.example.nativeuitesting.espresso.matchers

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

/**
 * Layout-related matchers (size, position, orientation).
 */
object LayoutMatchers {

    @JvmStatic
    fun withOrientation(orientation: Int): Matcher<View> {
        return object : BoundedMatcher<View, LinearLayout>(LinearLayout::class.java) {
            override fun describeTo(description: Description) {
                val orientationName = if (orientation == LinearLayout.HORIZONTAL) "HORIZONTAL" else "VERTICAL"
                description.appendText("with orientation: $orientationName")
            }

            override fun matchesSafely(linearLayout: LinearLayout): Boolean {
                return linearLayout.orientation == orientation
            }
        }
    }

    @JvmStatic
    fun isHorizontal(): Matcher<View> = withOrientation(LinearLayout.HORIZONTAL)

    @JvmStatic
    fun isVertical(): Matcher<View> = withOrientation(LinearLayout.VERTICAL)

    @JvmStatic
    fun withWidth(width: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with width: $width")
            }

            override fun matchesSafely(view: View): Boolean {
                return view.width == width
            }
        }
    }

    @JvmStatic
    fun withHeight(height: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with height: $height")
            }

            override fun matchesSafely(view: View): Boolean {
                return view.height == height
            }
        }
    }

    @JvmStatic
    fun withMinWidth(minWidth: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with minimum width: $minWidth")
            }

            override fun matchesSafely(view: View): Boolean {
                return view.width >= minWidth
            }
        }
    }

    @JvmStatic
    fun withMinHeight(minHeight: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with minimum height: $minHeight")
            }

            override fun matchesSafely(view: View): Boolean {
                return view.height >= minHeight
            }
        }
    }
}