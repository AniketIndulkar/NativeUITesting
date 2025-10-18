package com.example.nativeuitesting.espresso.config

/**
 * Timeout constants for various operations.
 */
object TimeoutConfig {

    // View waiting timeouts
    const val DEFAULT_WAIT_TIMEOUT_MS = 5000L
    const val SHORT_WAIT_TIMEOUT_MS = 2000L
    const val LONG_WAIT_TIMEOUT_MS = 10000L
    const val VERY_LONG_WAIT_TIMEOUT_MS = 30000L

    // Animation timeouts
    const val ANIMATION_TIMEOUT_MS = 1000L
    const val SHORT_ANIMATION_MS = 300L
    const val MEDIUM_ANIMATION_MS = 500L
    const val LONG_ANIMATION_MS = 1000L

    // Network timeouts
    const val NETWORK_TIMEOUT_MS = 15000L
    const val API_CALL_TIMEOUT_MS = 10000L

    // Dialog timeouts
    const val DIALOG_APPEAR_TIMEOUT_MS = 3000L
    const val DIALOG_DISMISS_TIMEOUT_MS = 2000L

    // RecyclerView timeouts
    const val RECYCLER_LOAD_TIMEOUT_MS = 5000L
    const val RECYCLER_SCROLL_TIMEOUT_MS = 3000L

    // ViewPager timeouts
    const val VIEWPAGER_SCROLL_TIMEOUT_MS = 2000L

    // Drawer timeouts
    const val DRAWER_OPEN_TIMEOUT_MS = 2000L
    const val DRAWER_CLOSE_TIMEOUT_MS = 2000L

    // Activity launch timeout
    const val ACTIVITY_LAUNCH_TIMEOUT_MS = 5000L

    // Keyboard timeout
    const val KEYBOARD_TIMEOUT_MS = 2000L
}