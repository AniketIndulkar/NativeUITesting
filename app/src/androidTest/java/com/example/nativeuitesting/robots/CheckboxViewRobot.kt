package com.example.nativeuitesting.robots

import com.example.nativeuitesting.espresso.actions.ClickActions
import com.example.nativeuitesting.espresso.actions.WaitActions
import com.example.nativeuitesting.espresso.assertions.TextAssertions
import com.example.nativeuitesting.espresso.assertions.ViewAssertions
import com.example.nativeuitesting.espresso.assertions.ViewAssertions.assertDisplayed


/**
 * Robot for Checkbox component screen.
 * Handles checkbox interactions and validations.
 */
class CheckboxViewRobot {

    // Checkbox IDs from your XML layout
    private val javaCheckboxId = android.R.id.checkbox
    private val kotlinCheckboxId = android.R.id.checkbox
    private val reactNativeCheckboxId = android.R.id.checkbox

    /**
     * Verify checkbox screen is visible
     */
    fun visible() = apply {
        WaitActions.waitForView(javaCheckboxId)
        ViewAssertions.assertDisplayed(javaCheckboxId)
    }

    /**
     * Click Java checkbox
     */
    fun clickJavaCheckbox() = apply {
        ClickActions.clickOn(javaCheckboxId)
    }

    /**
     * Click Kotlin checkbox
     */
    fun clickKotlinCheckbox() = apply {
        ClickActions.clickOn(kotlinCheckboxId)
    }

    /**
     * Click React Native checkbox
     */
    fun clickReactNativeCheckbox() = apply {
        ClickActions.clickOn(reactNativeCheckboxId)
    }

    /**
     * Assert Java checkbox is checked
     */
    fun assertJavaCheckboxChecked() = apply {
        ViewAssertions.assertChecked(javaCheckboxId)
    }

    /**
     * Assert Java checkbox is unchecked
     */
    fun assertJavaCheckboxUnchecked() = apply {
        ViewAssertions.assertNotChecked(javaCheckboxId)
    }

    /**
     * Assert Kotlin checkbox is checked
     */
    fun assertKotlinCheckboxChecked() = apply {
        ViewAssertions.assertChecked(kotlinCheckboxId)
    }

    /**
     * Assert Kotlin checkbox is unchecked
     */
    fun assertKotlinCheckboxUnchecked() = apply {
        ViewAssertions.assertNotChecked(kotlinCheckboxId)
    }

    /**
     * Assert React Native checkbox is checked
     */
    fun assertReactNativeCheckboxChecked() = apply {
        ViewAssertions.assertChecked(reactNativeCheckboxId)
    }

    /**
     * Assert React Native checkbox is unchecked
     */
    fun assertReactNativeCheckboxUnchecked() = apply {
        ViewAssertions.assertNotChecked(reactNativeCheckboxId)
    }

    /**
     * Assert checkbox label text is displayed
     */
    fun assertCheckboxLabel(text: String) = apply {
        assertDisplayed(text)
    }

    /**
     * Assert all checkboxes are visible
     */
    fun assertAllCheckboxesVisible() = apply {
        ViewAssertions.assertDisplayed(javaCheckboxId)
        ViewAssertions.assertDisplayed(kotlinCheckboxId)
        ViewAssertions.assertDisplayed(reactNativeCheckboxId)
    }

    /**
     * Assert checkbox state
     */
    fun assertCheckboxState(checkboxId: Int, isChecked: Boolean) = apply {
        if (isChecked) ViewAssertions.assertChecked(checkboxId) else ViewAssertions.assertNotChecked(checkboxId)
    }

    /**
     * Toggle checkbox and verify state change
     */
    fun toggleCheckboxAndVerify(checkboxId: Int, expectedState: Boolean) = apply {
        ClickActions.clickOn(checkboxId)
        assertCheckboxState(checkboxId, expectedState)
    }
}

/**
 * DSL entry point
 */
fun checkboxScreen(func: CheckboxViewRobot.() -> Unit) =
    CheckboxViewRobot().apply(func)