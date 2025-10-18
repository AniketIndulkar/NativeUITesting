package com.example.nativeuitesting.espresso.utils

import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiObjectNotFoundException
import androidx.test.uiautomator.UiSelector

/**
 * Utility functions for handling permissions in tests.
 */
object PermissionUtils {

    @JvmStatic
    fun grantPermission(permission: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val instrumentation = InstrumentationRegistry.getInstrumentation()
            val packageName = instrumentation.targetContext.packageName
            val uiDevice = UiDevice.getInstance(instrumentation)

            try {
                uiDevice.executeShellCommand("pm grant $packageName $permission")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @JvmStatic
    fun revokePermission(permission: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val instrumentation = InstrumentationRegistry.getInstrumentation()
            val packageName = instrumentation.targetContext.packageName
            val uiDevice = UiDevice.getInstance(instrumentation)

            try {
                uiDevice.executeShellCommand("pm revoke $packageName $permission")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    @JvmStatic
    fun allowPermissionsIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

            try {
                val allowButton: UiObject = device.findObject(
                    UiSelector()
                        .textMatches("(?i)(allow|permit|ok)")
                        .className("android.widget.Button")
                )

                if (allowButton.exists() && allowButton.isEnabled) {
                    allowButton.click()
                    device.waitForIdle()
                }
            } catch (e: UiObjectNotFoundException) {
                // Permission dialog not found, continue
            }
        }
    }

    @JvmStatic
    fun denyPermissionsIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

            try {
                val denyButton: UiObject = device.findObject(
                    UiSelector()
                        .textMatches("(?i)(deny|don't allow|cancel)")
                        .className("android.widget.Button")
                )

                if (denyButton.exists() && denyButton.isEnabled) {
                    denyButton.click()
                    device.waitForIdle()
                }
            } catch (e: UiObjectNotFoundException) {
                // Permission dialog not found, continue
            }
        }
    }
}