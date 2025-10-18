package com.example.nativeuitesting.espresso.actions

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.*

/**
 * General UI actions.
 */
object GeneralActions {

    @JvmStatic
    fun pressBack() {
        androidx.test.espresso.Espresso.pressBack()
    }

    @JvmStatic
    fun pressKey(keyCode: Int) {
        onView(withId(android.R.id.content)).perform(ViewActions.pressKey(keyCode))
    }

    @JvmStatic
    fun setSwitchState(@IdRes switchId: Int, checked: Boolean) {
        onView(withId(switchId)).perform(setSwitchToState(checked))
    }

    private fun setSwitchToState(checked: Boolean) = ViewActions.actionWithAssertions(
        object : androidx.test.espresso.ViewAction {
            override fun getConstraints() = isDisplayed()

            override fun getDescription() = "Set switch to ${if (checked) "checked" else "unchecked"}"

            override fun perform(uiController: androidx.test.espresso.UiController, view: View) {
                val switchView = view as? android.widget.Switch
                    ?: throw IllegalArgumentException("View must be a Switch")

                if (switchView.isChecked != checked) {
                    view.performClick()
                }
            }
        }
    )
}