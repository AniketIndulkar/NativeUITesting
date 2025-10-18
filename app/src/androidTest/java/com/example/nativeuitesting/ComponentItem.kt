package com.example.nativeuitesting

/**
 * Enum representing all available UI components in the app.
 * Used for type-safe navigation in tests.
 *
 * Each enum value maps to the display name shown in the component list.
 */
enum class ComponentItem(val displayName: String) {
    // Dialog & Overlays
    ALERTDIALOG("Alert Dialog"),
    BOTTOMSHEET("Bottom Sheet"),
    TOAST("Toast"),
    TOOLTIP("Tooltip"),

    // Input Components
    BUTTON("Button"),
    CHECKBOX("Checkbox"),
    INPUTFIELD("Input Field"),
    RADIOBUTTON("Radio Button"),
    SWITCH("Switch"),
    SLIDER("Slider"),

    // Selection & Pickers
    CHIPS("Chips"),
    DROPDOWN("Dropdown"),
    DATEPICKER("Date Picker"),
    TIMEPICKER("Time Picker"),

    // Layout & Navigation
    ACCORDION("Accordion"),
    TABS("Tabs"),
    NAVIGATION("Navigation"),

    // Lists & Data
    RECYCLERVIEW("RecyclerView"),
    CARD("Card");

    companion object {
        /**
         * Find ComponentItem by its display name
         * @return ComponentItem or null if not found
         */
        fun fromDisplayName(name: String): ComponentItem? {
            return values().find { it.displayName.equals(name, ignoreCase = true) }
        }

        /**
         * Get all component display names
         */
        fun getAllDisplayNames(): List<String> {
            return values().map { it.displayName }
        }
    }
}