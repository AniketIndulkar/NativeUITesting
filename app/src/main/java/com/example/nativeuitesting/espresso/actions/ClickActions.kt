package com.example.nativeuitesting.espresso.actions
import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher

/**
 * Click actions for UI testing.
 *
 * Usage:
 * ```
 * import com.mobile.core.uitesting.espresso.actions.ClickActions.*
 *
 * clickOn(R.id.button)
 * doubleClickOn("Submit")
 * longClickOn(R.id.menu_item)
 * ```
 */
object ClickActions {

    /**
     * Performs a single click on a view with the given resource ID
     */
    @JvmStatic
    fun clickOn(@IdRes viewId: Int) {
        onView(withId(viewId)).perform(ViewActions.click())
    }

    /**
     * Performs a single click on a view with the given text
     */
    @JvmStatic
    fun clickOn(text: String) {
        onView(withText(text)).perform(ViewActions.click())
    }

    /**
     * Performs a single click on a view matching the given matcher
     */
    @JvmStatic
    fun clickOn(matcher: Matcher<View>) {
        onView(matcher).perform(ViewActions.click())
    }

    /**
     * Performs a double click on a view with the given resource ID
     */
    @JvmStatic
    fun doubleClickOn(@IdRes viewId: Int) {
        onView(withId(viewId)).perform(ViewActions.doubleClick())
    }

    /**
     * Performs a double click on a view with the given text
     */
    @JvmStatic
    fun doubleClickOn(text: String) {
        onView(withText(text)).perform(ViewActions.doubleClick())
    }

    /**
     * Performs a long click on a view with the given resource ID
     */
    @JvmStatic
    fun longClickOn(@IdRes viewId: Int) {
        onView(withId(viewId)).perform(ViewActions.longClick())
    }

    /**
     * Performs a long click on a view with the given text
     */
    @JvmStatic
    fun longClickOn(text: String) {
        onView(withText(text)).perform(ViewActions.longClick())
    }

    /**
     * Clicks on a clickable span within a TextView
     */
    @JvmStatic
    fun clickSpannable(@IdRes viewId: Int, textToClick: String) {
        onView(withId(viewId)).perform(clickOnSpannable(textToClick))
    }

    private fun clickOnSpannable(textToClick: String) = ViewActions.actionWithAssertions(
        object : androidx.test.espresso.ViewAction {
            override fun getConstraints() = isDisplayed()

            override fun getDescription() = "Click on clickable span with text: $textToClick"

            override fun perform(uiController: androidx.test.espresso.UiController, view: View) {
                val textView = view as? android.widget.TextView
                    ?: throw IllegalArgumentException("View must be a TextView")

                val spannableString = textView.text as? android.text.SpannableString
                    ?: throw IllegalArgumentException("TextView text must be SpannableString")

                val spans = spannableString.getSpans(
                    0,
                    spannableString.length,
                    android.text.style.ClickableSpan::class.java
                )

                for (span in spans) {
                    val start = spannableString.getSpanStart(span)
                    val end = spannableString.getSpanEnd(span)
                    val spanText = spannableString.subSequence(start, end).toString()

                    if (spanText == textToClick) {
                        span.onClick(view)
                        return
                    }
                }

                throw IllegalStateException("No clickable span found with text: $textToClick")
            }
        }
    )
}