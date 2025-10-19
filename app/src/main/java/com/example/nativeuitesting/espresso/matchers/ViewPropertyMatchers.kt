package com.example.nativeuitesting.espresso.matchers

import android.view.View
import android.widget.CheckBox
import android.widget.Checkable
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

/**
 * Basic view matchers for common view properties.
 *
 * For text-specific matchers, see TextMatchers
 * For color matchers, see ColorMatchers
 * For drawable matchers, see DrawableMatchers
 *
 * Usage:
 * ```
 * import com.mobile.core.uitesting.espresso.matchers.ViewMatchers.*
 *
 * val matcher = hasAnyText()
 * val tagMatcher = withTag("my_tag")
 * val alphaMatcher = withAlpha(0.5f)
 * ```
 */
object ViewPropertyMatchers {

    /**
     * Matches TextView that has any text (non-empty)
     */
    @JvmStatic
    fun hasAnyText(): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has any text (non-empty)")
            }

            override fun matchesSafely(textView: TextView): Boolean {
                return !textView.text.isNullOrEmpty()
            }
        }
    }

    /**
     * Matches TextView that has no text (empty or null)
     */
    @JvmStatic
    fun hasNoText(): Matcher<View> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has no text (empty or null)")
            }

            override fun matchesSafely(textView: TextView): Boolean {
                return textView.text.isNullOrEmpty()
            }
        }
    }

    /**
     * Matches view with the given alpha value
     */
    @JvmStatic
    fun withAlpha(alpha: Float): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with alpha: $alpha")
            }

            override fun matchesSafely(view: View): Boolean {
                return view.alpha == alpha
            }
        }
    }

    /**
     * Matches view with alpha greater than or equal to threshold
     */
    @JvmStatic
    fun withMinAlpha(minAlpha: Float): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with minimum alpha: $minAlpha")
            }

            override fun matchesSafely(view: View): Boolean {
                return view.alpha >= minAlpha
            }
        }
    }

    /**
     * Matches view with the given tag
     */
    @JvmStatic
    fun withTag(tag: Any): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with tag: $tag")
            }

            override fun matchesSafely(view: View): Boolean {
                return view.tag == tag
            }
        }
    }

    /**
     * Matches view with a tag key and value
     */
    @JvmStatic
    fun withTagKey(key: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with tag key: $key")
            }

            override fun matchesSafely(view: View): Boolean {
                return view.getTag(key) != null
            }
        }
    }

    /**
     * Matches view with a tag key and specific value
     */
    @JvmStatic
    fun withTagKeyValue(key: Int, value: Any): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with tag key $key and value: $value")
            }

            override fun matchesSafely(view: View): Boolean {
                return view.getTag(key) == value
            }
        }
    }

    /**
     * Matches view with elevation
     */
    @JvmStatic
    fun withElevation(elevation: Float): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with elevation: $elevation")
            }

            override fun matchesSafely(view: View): Boolean {
                return view.elevation == elevation
            }
        }
    }

    /**
     * Matches view with minimum elevation
     */
    @JvmStatic
    fun withMinElevation(minElevation: Float): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("with minimum elevation: $minElevation")
            }

            override fun matchesSafely(view: View): Boolean {
                return view.elevation >= minElevation
            }
        }
    }

    /**
     * Matches view that is focusable
     */
    @JvmStatic
    fun isFocusable(): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("is focusable")
            }

            override fun matchesSafely(view: View): Boolean {
                return view.isFocusable
            }
        }
    }

    /**
     * Matches view that is focusable in touch mode
     */
    @JvmStatic
    fun isFocusableInTouchMode(): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("is focusable in touch mode")
            }

            override fun matchesSafely(view: View): Boolean {
                return view.isFocusableInTouchMode
            }
        }
    }

    /**
     * Matcher for checked
     */
    fun isChecked(): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("is checked")
            }

            override fun matchesSafely(view: View): Boolean {
                return if (view is Checkable) {
                    view.isChecked
                } else {
                    false
                }
            }
        }
    }

    /**
     * Matcher for unchecked
     */
    fun isNotChecked(): Matcher<View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("is not checked")
            }

            override fun matchesSafely(view: View): Boolean {
                return if (view is Checkable) {
                    !view.isChecked
                } else {
                    false
                }
            }
        }
    }

    /**
     * Matcher for checkbox state
     */
    fun hasCheckedState(isChecked: Boolean): Matcher<View> {
        return if (isChecked) isChecked() else isNotChecked()
    }
}