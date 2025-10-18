package com.example.nativeuitesting.espresso.utils


import android.graphics.Bitmap
import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.screenshot.Screenshot
import androidx.test.uiautomator.UiDevice
import java.io.File
import java.io.FileOutputStream

/**
 * Utility functions for device operations.
 */
object DeviceUtils {

    @JvmStatic
    fun getDevice(): UiDevice {
        return UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @JvmStatic
    fun pressBack() {
        getDevice().pressBack()
    }

    @JvmStatic
    fun pressHome() {
        getDevice().pressHome()
    }

    @JvmStatic
    fun pressRecentApps() {
        getDevice().pressRecentApps()
    }

    @JvmStatic
    fun wakeUp() {
        val device = getDevice()
        if (!device.isScreenOn) {
            device.wakeUp()
        }
    }

    @JvmStatic
    fun sleep() {
        val device = getDevice()
        if (device.isScreenOn) {
            device.sleep()
        }
    }

    @JvmStatic
    fun takeScreenshot(fileName: String): File? {
        try {
            val screenshot = Screenshot.capture()
            screenshot.name = fileName
            screenshot.format = Bitmap.CompressFormat.PNG

            val capture = screenshot
            val processor = androidx.test.runner.screenshot.BasicScreenCaptureProcessor()

            return processor.process(capture) as File?
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    @JvmStatic
    fun getDeviceModel(): String = Build.MODEL

    @JvmStatic
    fun getAndroidVersion(): Int = Build.VERSION.SDK_INT

    @JvmStatic
    fun getScreenWidth(): Int = getDevice().displayWidth

    @JvmStatic
    fun getScreenHeight(): Int = getDevice().displayHeight

    @JvmStatic
    fun isTablet(): Boolean {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        return context.resources.configuration.smallestScreenWidthDp >= 600
    }
}