package com.example.nativeuitesting.espresso.utils

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage

/**
 * Utility functions for changing device orientation in tests.
 */
object OrientationUtils {

    @JvmStatic
    fun setLandscape() {
        setOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    }

    @JvmStatic
    fun setPortrait() {
        setOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }

    @JvmStatic
    fun rotateLandscape() {
        setLandscape()
        Thread.sleep(500) // Wait for rotation animation
    }

    @JvmStatic
    fun rotatePortrait() {
        setPortrait()
        Thread.sleep(500) // Wait for rotation animation
    }

    @JvmStatic
    fun setOrientation(orientation: Int) {
        val activity = getCurrentActivity()
        activity?.requestedOrientation = orientation
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
    }

    @JvmStatic
    fun getCurrentOrientation(): Int? {
        return getCurrentActivity()?.requestedOrientation
    }

    private fun getCurrentActivity(): Activity? {
        var currentActivity: Activity? = null
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            val activities = ActivityLifecycleMonitorRegistry.getInstance()
                .getActivitiesInStage(Stage.RESUMED)
            if (activities.isNotEmpty()) {
                currentActivity = activities.iterator().next()
            }
        }
        return currentActivity
    }
}