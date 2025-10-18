package com.example.nativeuitesting.robots

import com.example.nativeuitesting.R
import com.example.nativeuitesting.espresso.config.TimeoutConfig
import com.example.nativeuitesting.espresso.dsl.BaseRobot


/**
 * Robot for the Alert Dialog component screen.
 *
 * This screen displays a list of different alert dialog examples.
 * Each item can be clicked to show the corresponding dialog.
 *
 * Example dialogs:
 * - Dialog with 2 actions (Done/Close)
 * - Dialog with 1 action (OK)
 * - Dialog with HTML text
 * - Custom styled dialogs
 */
class AlertDialogRobot : BaseRobot() {

    // TODO: Update these IDs with your actual resource IDs
    private val recyclerViewId = android.R.id.list
    private val dialogHeaderId = android.R.id.text1
    private val dialogMessageId = android.R.id.text2

    /**
     * Verifies the Alert Dialog screen is visible
     */
    fun visible() {
        waitForView(recyclerViewId, TimeoutConfig.DEFAULT_WAIT_TIMEOUT_MS)
        assertDisplayed(recyclerViewId)
    }

    /**
     * Opens a specific dialog from the list by its label/title
     *
     * @param label The text of the dialog item to click (e.g., "Dialog with x2 actions")
     */
    fun openDialog(label: String) {
        // Ensure we're on the list screen
        waitForView(recyclerViewId)

        // Scroll to the dialog item (in case list is long)
        scrollRecyclerToItemWithText(recyclerViewId, label)

        // Click on the dialog item
        clickRecyclerItemWithText(recyclerViewId, label)

        // Wait for dialog to appear with longer timeout for dialogs
        waitForView(dialogHeaderId, TimeoutConfig.DIALOG_APPEAR_TIMEOUT_MS)
    }

    /**
     * Verifies the dialog is currently displayed
     */
    fun assertDialogDisplayed() {
        assertDisplayed(dialogHeaderId)
    }

    /**
     * Asserts the dialog header/title matches expected text
     *
     * @param expectedText The expected header text
     */
    fun assertDialogHeader(expectedText: String) {
        assertText(dialogHeaderId, expectedText)
    }

    /**
     * Asserts the dialog message/content matches expected text
     *
     * @param expectedMessage The expected message text
     */
    fun assertDialogMessage(expectedMessage: String) {
        assertText(dialogMessageId, expectedMessage)
    }

    /**
     * Asserts the dialog message contains specific text
     *
     * @param substring Text that should be present in the message
     */
    fun assertDialogMessageContains(substring: String) {
        assertTextContains(dialogMessageId, substring)
    }

    /**
     * Asserts both action buttons are visible with correct text
     *
     * @param positiveButtonText Text of the positive button (e.g., "Done", "OK")
     * @param negativeButtonText Text of the negative button (e.g., "Close", "Cancel")
     */
    fun assertDialogActionButtons(positiveButtonText: String, negativeButtonText: String) {
        assertDisplayed(positiveButtonText)
        assertDisplayed(negativeButtonText)
    }

    /**
     * Clicks the positive button in the dialog
     *
     * @param buttonText The text of the button to click (default: "Done")
     */
    fun clickPositiveButton(buttonText: String = "Done") {
        clickOn(buttonText)

        // Wait for dialog to dismiss
        waitUntilGone(dialogHeaderId, TimeoutConfig.DIALOG_DISMISS_TIMEOUT_MS)

        // Ensure we're back to the list
        waitForView(recyclerViewId)
    }

    /**
     * Clicks the negative button in the dialog
     *
     * @param buttonText The text of the button to click (default: "Close")
     */
    fun clickNegativeButton(buttonText: String = "Close") {
        clickOn(buttonText)

        // Wait for dialog to dismiss
        waitUntilGone(dialogHeaderId, TimeoutConfig.DIALOG_DISMISS_TIMEOUT_MS)

        // Ensure we're back to the list
        waitForView(recyclerViewId)
    }

    /**
     * Verifies the dialog has been dismissed and we're back to the list
     */
    fun assertDialogDismissed() {
        waitUntilGone(dialogHeaderId, TimeoutConfig.DIALOG_DISMISS_TIMEOUT_MS)
        assertDisplayed(recyclerViewId)
    }

    /**
     * Clicks outside the dialog to dismiss it (if dismissible)
     * Note: This might not work for all dialog types
     */
    fun dismissByClickingOutside() {
        pressBack()
        waitUntilGone(dialogHeaderId, TimeoutConfig.DIALOG_DISMISS_TIMEOUT_MS)
    }
}

/**
 * DSL entry point for AlertDialogRobot.
 *
 * Usage:
 * ```
 * alertDialogScreen {
 *     visible()
 *     openDialog("Dialog with x2 actions")
 *     assertDialogHeader("Dialog with x2 actions")
 *     clickPositiveButton("Done")
 *     assertDialogDismissed()
 * }
 * ```
 */
fun alertDialogScreen(func: AlertDialogRobot.() -> Unit) =
    AlertDialogRobot().apply(func)