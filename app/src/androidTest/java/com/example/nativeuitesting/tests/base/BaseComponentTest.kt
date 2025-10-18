package com.example.nativeuitesting.tests.base

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.nativeuitesting.MainActivity
import com.example.nativeuitesting.espresso.rules.DisableAnimationsRule
import org.junit.Rule

/**
 * Base class for all component UI tests.
 *
 * Provides common test rules and setup:
 * - ActivityScenarioRule: Launches MainActivity
 * - DisableAnimationsRule: Disables animations for faster, more reliable tests
 * - ScreenshotOnFailureRule: Captures screenshot when test fails
 *
 * All component test classes should extend this.
 */
abstract class BaseComponentTest {

    /**
     * Launches MainActivity before each test
     */
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * Disables system animations for test stability
     */
    @get:Rule
    val animationsRule = DisableAnimationsRule()

}