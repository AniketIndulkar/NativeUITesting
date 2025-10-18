package com.example.nativeuitesting.espresso.config

/**
 * Global test configuration.
 */
object TestConfig {

    // Animation settings
    const val ANIMATIONS_DISABLED = true

    // Screenshot settings
    const val SCREENSHOT_ON_FAILURE = true
    const val SCREENSHOT_ON_SUCCESS = false

    // Retry settings
    const val DEFAULT_RETRY_COUNT = 3
    const val RETRY_DELAY_MS = 1000L

    // Logging
    const val VERBOSE_LOGGING = true
    const val LOG_TAG = "EspressoTest"

    // Test data
    const val TEST_EMAIL = "test@example.com"
    const val TEST_PASSWORD = "TestPassword123"
    const val TEST_USERNAME = "testuser"
}