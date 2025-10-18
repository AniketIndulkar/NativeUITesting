package com.example.nativeuitesting.espresso.builders

import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers
import com.example.nativeuitesting.espresso.matchers.ColorMatchers
import com.example.nativeuitesting.espresso.matchers.DrawableMatchers
import com.example.nativeuitesting.espresso.matchers.ViewPropertyMatchers
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf

/**
 * Fluent builder for creating complex view matchers with chainable methods.
 *
 * Usage:
 * ```
 * ViewBuilder()
 *     .withId(R.id.button)
 *     .withText("Submit")
 *     .isEnabled()
 *     .perform(click())
 *
 * // Or using the helper function:
 * viewBuilder {
 *     withId(R.id.button)
 *     withText("Submit")
 *     isEnabled()
 * }.perform(click())
 * ```
 */
class ViewBuilder {
    private val matchers = mutableListOf<Matcher<View>>()

    // ============================================
    // BASIC MATCHERS - ALL RETURN ViewBuilder
    // ============================================

    fun withId(@IdRes id: Int): ViewBuilder {
        matchers.add(ViewMatchers.withId(id))
        return this
    }

    fun withText(text: String): ViewBuilder {
        matchers.add(ViewMatchers.withText(text))
        return this
    }

    fun withHint(hint: String): ViewBuilder {
        matchers.add(ViewMatchers.withHint(hint))
        return this
    }

    fun withContentDescription(description: String): ViewBuilder {
        matchers.add(ViewMatchers.withContentDescription(description))
        return this
    }

    fun withResourceName(name: String): ViewBuilder {
        matchers.add(ViewMatchers.withResourceName(name))
        return this
    }

    fun withClassName(className: String): ViewBuilder {
        matchers.add(ViewMatchers.withClassName(Matchers.`is`(className)))
        return this
    }

    // ============================================
    // STATE MATCHERS - ALL RETURN ViewBuilder
    // ============================================

    fun isDisplayed(): ViewBuilder {
        matchers.add(ViewMatchers.isDisplayed())
        return this
    }

    fun isCompletelyDisplayed(): ViewBuilder {
        matchers.add(ViewMatchers.isCompletelyDisplayed())
        return this
    }

    fun isEnabled(): ViewBuilder {
        matchers.add(ViewMatchers.isEnabled())
        return this
    }

    fun isDisabled(): ViewBuilder {
        matchers.add(Matchers.not(ViewMatchers.isEnabled()))
        return this
    }

    fun isClickable(): ViewBuilder {
        matchers.add(ViewMatchers.isClickable())
        return this
    }

    fun isChecked(): ViewBuilder {
        matchers.add(ViewMatchers.isChecked())
        return this
    }

    fun isNotChecked(): ViewBuilder {
        matchers.add(Matchers.not(ViewMatchers.isChecked()))
        return this
    }

    fun isSelected(): ViewBuilder {
        matchers.add(ViewMatchers.isSelected())
        return this
    }

    fun isFocusable(): ViewBuilder {
        matchers.add(ViewMatchers.isFocusable())
        return this
    }

    // ============================================
    // TEXT MATCHERS - ALL RETURN ViewBuilder
    // ============================================

    fun containsText(substring: String): ViewBuilder {
        matchers.add(ViewMatchers.withText(Matchers.containsString(substring)))
        return this
    }

    fun hasAnyText(): ViewBuilder {
        matchers.add(ViewPropertyMatchers.hasAnyText())
        return this
    }

    fun hasNoText(): ViewBuilder {
        matchers.add(ViewPropertyMatchers.hasNoText())
        return this
    }

    // ============================================
    // HIERARCHY MATCHERS - ALL RETURN ViewBuilder
    // ============================================

    fun hasDescendant(descendantMatcher: Matcher<View>): ViewBuilder {
        matchers.add(ViewMatchers.hasDescendant(descendantMatcher))
        return this
    }

    /**
     * Nested builder for descendant
     */
    fun hasDescendant(block: ViewBuilder.() -> Unit): ViewBuilder {
        val descendantBuilder = ViewBuilder().apply(block)
        matchers.add(ViewMatchers.hasDescendant(descendantBuilder.build()))
        return this
    }

    fun hasSibling(siblingMatcher: Matcher<View>): ViewBuilder {
        matchers.add(ViewMatchers.hasSibling(siblingMatcher))
        return this
    }

    /**
     * Nested builder for sibling
     */
    fun hasSibling(block: ViewBuilder.() -> Unit): ViewBuilder {
        val siblingBuilder = ViewBuilder().apply(block)
        matchers.add(ViewMatchers.hasSibling(siblingBuilder.build()))
        return this
    }

    fun withParent(parentMatcher: Matcher<View>): ViewBuilder {
        matchers.add(ViewMatchers.withParent(parentMatcher))
        return this
    }

    /**
     * Nested builder for parent
     */
    fun withParent(block: ViewBuilder.() -> Unit): ViewBuilder {
        val parentBuilder = ViewBuilder().apply(block)
        matchers.add(ViewMatchers.withParent(parentBuilder.build()))
        return this
    }

    // ============================================
    // VISUAL MATCHERS - ALL RETURN ViewBuilder
    // ============================================

    fun withDrawable(@DrawableRes drawableId: Int): ViewBuilder {
        matchers.add(DrawableMatchers.withDrawable(drawableId))
        return this
    }

    fun withBackgroundColor(@ColorInt color: Int): ViewBuilder {
        matchers.add(ColorMatchers.withBackgroundColor(color))
        return this
    }

    // ============================================
    // CUSTOM MATCHER - RETURNS ViewBuilder
    // ============================================

    fun withMatcher(matcher: Matcher<View>): ViewBuilder {
        matchers.add(matcher)
        return this
    }

    // ============================================
    // TERMINAL METHODS
    // ============================================

    /**
     * Builds and returns the combined matcher
     */
    fun build(): Matcher<View> {
        return when {
            matchers.isEmpty() -> Matchers.anything()
            matchers.size == 1 -> matchers.first()
            else -> allOf(matchers)
        } as Matcher<View>
    }

    /**
     * Returns ViewInteraction for immediate use
     */
    fun toInteraction(): ViewInteraction {
        return onView(build())
    }

    /**
     * Performs an action directly (terminal operation)
     */
    fun perform(action: ViewAction): ViewInteraction {
        return toInteraction().perform(action)
    }

    /**
     * Performs multiple actions directly (terminal operation)
     */
    fun perform(vararg actions: ViewAction): ViewInteraction {
        return toInteraction().perform(*actions)
    }

    /**
     * Checks an assertion directly (terminal operation)
     */
    fun check(assertion: ViewAssertion): ViewInteraction {
        return toInteraction().check(assertion)
    }
}

// ============================================
// DSL HELPER FUNCTIONS
// ============================================

/**
 * Creates a ViewBuilder with DSL syntax
 *
 * Usage:
 * ```
 * viewBuilder {
 *     withId(R.id.button)
 *     withText("Submit")
 *     isEnabled()
 * }.perform(click())
 * ```
 */
fun viewBuilder(block: ViewBuilder.() -> Unit): ViewBuilder {
    return ViewBuilder().apply(block)
}

/**
 * Quick shorthand for finding view by ID
 */
fun viewWithId(@IdRes id: Int): ViewBuilder {
    return ViewBuilder().withId(id)
}

/**
 * Quick shorthand for finding view by text
 */
fun viewWithText(text: String): ViewBuilder {
    return ViewBuilder().withText(text)
}