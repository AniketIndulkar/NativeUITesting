package com.example.nativeuitesting.espresso.rules

import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Automatically grants runtime permissions for tests.
 *
 * Usage:
 * ```
 * @get:Rule
 * val permissionsRule = GrantPermissionsRule(
 *     android.Manifest.permission.CAMERA,
 *     android.Manifest.permission.ACCESS_FINE_LOCATION
 * )
 * ```
 */
class GrantPermissionsRule(private vararg val permissions: String) : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                grantPermissions()
                try {
                    base.evaluate()
                } finally {
                    // Optionally revoke permissions after test
                }
            }
        }
    }

    private fun grantPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val instrumentation = InstrumentationRegistry.getInstrumentation()
            val packageName = instrumentation.targetContext.packageName
            val uiDevice = UiDevice.getInstance(instrumentation)

            permissions.forEach { permission ->
                val command = "pm grant $packageName $permission"
                try {
                    uiDevice.executeShellCommand(command)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}