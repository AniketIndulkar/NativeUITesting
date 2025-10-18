package com.example.nativeuitesting.espresso.rules

import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.io.IOException

/**
 * Disables system animations during tests.
 */
class DisableAnimationsRule : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                disableAnimations()
                try {
                    base.evaluate()
                } finally {
                    enableAnimations()
                }
            }
        }
    }

    private fun disableAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            executeShellCommand("settings put global window_animation_scale 0")
            executeShellCommand("settings put global transition_animation_scale 0")
            executeShellCommand("settings put global animator_duration_scale 0")
        }
    }

    private fun enableAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            executeShellCommand("settings put global window_animation_scale 1")
            executeShellCommand("settings put global transition_animation_scale 1")
            executeShellCommand("settings put global animator_duration_scale 1")
        }
    }

    private fun executeShellCommand(command: String) {
        try {
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
                .executeShellCommand(command)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}