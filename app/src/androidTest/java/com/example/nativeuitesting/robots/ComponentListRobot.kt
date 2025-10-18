package com.example.nativeuitesting.robots

import com.example.nativeuitesting.ComponentItem
import com.example.nativeuitesting.R
import com.example.nativeuitesting.espresso.dsl.BaseRobot


/**
 * Robot for the main Component List screen.
 *
 * This screen displays a list of all available UI components.
 * Users can click on a component to navigate to its detail screen.
 */
class ComponentListRobot : BaseRobot() {

    // TODO: Update this with your actual RecyclerView resource ID
    private val recyclerViewId = android.R.id.list


    /**
     * Verifies the component list screen is visible
     */
    fun visible() {
        waitForView(recyclerViewId)
        assertDisplayed(recyclerViewId)
    }

    /**
     * Opens a component by clicking on it in the list (type-safe with enum)
     *
     * @param item The component to open
     */
    fun open(item: ComponentItem) {
        scrollRecyclerToItemWithText(recyclerViewId, item.displayName)
        clickRecyclerItemWithText(recyclerViewId, item.displayName)
        sleep(500)  // Brief wait for navigation animation
    }

    /**
     * Opens a component by clicking on it in the list (by string name)
     *
     * @param componentName The display name of the component
     */
    fun open(componentName: String) {
        scrollRecyclerToItemWithText(recyclerViewId, componentName)
        clickRecyclerItemWithText(recyclerViewId, componentName)
        sleep(500)  // Brief wait for navigation animation
    }

    /**
     * Scrolls to a specific component in the list
     */
    fun scrollToComponent(item: ComponentItem) {
        scrollRecyclerToItemWithText(recyclerViewId, item.displayName)
    }

    /**
     * Verifies a component exists in the list
     */
    fun assertComponentExists(item: ComponentItem) {
        scrollToComponent(item)
        assertDisplayed(item.displayName)
    }

    /**
     * Verifies the list is not empty
     */
    fun assertListNotEmpty() {
        assertRecyclerNotEmpty(recyclerViewId)
    }

    /**
     * Verifies the list has a specific number of items
     */
    fun assertListItemCount(expectedCount: Int) {
        assertRecyclerItemCount(recyclerViewId, expectedCount)
    }
}

/**
 * DSL entry point for ComponentListRobot.
 *
 * Usage:
 * ```
 * componentListScreen {
 *     visible()
 *     open(ComponentItem.ALERTDIALOG)
 * }
 * ```
 */
fun componentListScreen(func: ComponentListRobot.() -> Unit) =
    ComponentListRobot().apply(func)