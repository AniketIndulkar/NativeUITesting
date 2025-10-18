package com.example.nativeuitesting.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nativeuitesting.ComponentItem
import com.example.nativeuitesting.robots.alertDialogScreen
import com.example.nativeuitesting.robots.componentListScreen
import com.example.nativeuitesting.tests.base.BaseComponentTest
import org.junit.Test
import org.junit.runner.RunWith

/**
 * UI tests for the Alert Dialog component.
 *
 * Tests various dialog configurations:
 * - Dialogs with 2 action buttons (positive/negative)
 * - Dialogs with 1 action button
 * - Dialogs with HTML/spannable text
 * - Dialog button interactions
 * - Dialog dismiss behavior
 *
 * Each test starts fresh with full navigation for true test isolation.
 */
@RunWith(AndroidJUnit4::class)
class AlertDialogTest : BaseComponentTest() {

    /**
     * Helper function to navigate to Alert Dialog component screen.
     * Called explicitly at the start of each test for clarity and isolation.
     */
    private fun navigateToAlertDialog() {
        componentListScreen {
            visible()
            open(ComponentItem.ALERTDIALOG)
        }
    }

    /**
     * Test: Dialog with 2 action buttons (positive and negative)
     *
     * Verifies:
     * - Can navigate to Alert Dialog screen
     * - Can open a dialog with 2 action buttons
     * - Dialog displays correct header text
     * - Both action buttons are visible with correct text
     * - Clicking positive button dismisses the dialog
     * - User is returned to the list after dismissal
     */
    @Test
    fun test_dialog_with_two_actions_positive_button() {
        navigateToAlertDialog()

        alertDialogScreen {
            visible()
            openDialog("Dialog with x2 actions")
            assertDialogDisplayed()
            assertDialogHeader("Dialog with x2 actions")
            assertDialogActionButtons(
                positiveButtonText = "Done",
                negativeButtonText = "Close"
            )
            clickPositiveButton("Done")
            assertDialogDismissed()
        }
    }

    /**
     * Test: Dialog with 2 actions - clicking negative button
     *
     * Verifies:
     * - Negative button (Close) works correctly
     * - Dialog dismisses when negative button is clicked
     */
    @Test
    fun test_dialog_with_two_actions_negative_button() {
        navigateToAlertDialog()

        alertDialogScreen {
            openDialog("Dialog with x2 actions")
            assertDialogDisplayed()
            assertDialogHeader("Dialog with x2 actions")
            clickNegativeButton("Close")
            assertDialogDismissed()
        }
    }

    /**
     * Test: Dialog with spannable/HTML formatted text
     *
     * Verifies:
     * - Can open dialog with HTML formatted text
     * - Dialog header displays correctly
     * - Can dismiss the dialog with positive button
     */
    @Test
    fun test_dialog_with_spannable_html_text() {
        navigateToAlertDialog()

        alertDialogScreen {
            visible()
            openDialog("Dialog with spannable HTML text!!")
            assertDialogDisplayed()
            assertDialogHeader("Dialog with spannable HTML text!!")
            clickPositiveButton("Done")
            assertDialogDismissed()
        }
    }

    /**
     * Test: Dialog with single action button
     *
     * Verifies:
     * - Can open dialog with only one action button
     * - Dialog displays correctly with single button
     * - Clicking the button dismisses the dialog
     */
    @Test
    fun test_dialog_with_single_action() {
        navigateToAlertDialog()

        alertDialogScreen {
            visible()
            openDialog("Dialog with x1 action")
            assertDialogDisplayed()
            assertDialogHeader("Dialog with x1 action")
            clickPositiveButton("OK")
            assertDialogDismissed()
        }
    }

    /**
     * Test: Opening multiple dialogs in sequence
     *
     * Verifies:
     * - Can open multiple dialogs one after another
     * - Each dialog works independently
     * - Dialog state is properly reset between opens
     * - No state pollution between dialogs
     */
    @Test
    fun test_multiple_dialogs_in_sequence() {
        navigateToAlertDialog()

        alertDialogScreen {
            // First dialog
            openDialog("Dialog with x2 actions")
            assertDialogHeader("Dialog with x2 actions")
            clickPositiveButton("Done")
            assertDialogDismissed()

            // Second dialog
            openDialog("Dialog with spannable HTML text!!")
            assertDialogHeader("Dialog with spannable HTML text!!")
            clickPositiveButton("Done")
            assertDialogDismissed()

            // Third dialog
            openDialog("Dialog with x1 action")
            assertDialogHeader("Dialog with x1 action")
            clickPositiveButton("OK")
            assertDialogDismissed()
        }
    }

    /**
     * Test: Dialog with custom message content
     *
     * Verifies:
     * - Dialog message content is displayed correctly
     * - Can verify both header and message text
     *
     * TODO: Update with actual message text from your dialog
     */
    @Test
    fun test_dialog_with_custom_message() {
        navigateToAlertDialog()

        alertDialogScreen {
            openDialog("Dialog with x2 actions")
            assertDialogHeader("Dialog with x2 actions")

            // TODO: Update with your actual dialog message
            // assertDialogMessage("Your dialog message here")

            clickPositiveButton("Done")
            assertDialogDismissed()
        }
    }

    /**
     * Test: Verify all dialog buttons are clickable
     *
     * Verifies:
     * - Both positive and negative buttons are present
     * - Buttons can be clicked without errors
     */
    @Test
    fun test_dialog_buttons_are_clickable() {
        navigateToAlertDialog()

        alertDialogScreen {
            openDialog("Dialog with x2 actions")
            assertDialogDisplayed()

            // Verify both buttons are displayed
            assertDialogActionButtons("Done", "Close")

            // Test negative button
            clickNegativeButton("Close")
            assertDialogDismissed()
        }
    }
}
