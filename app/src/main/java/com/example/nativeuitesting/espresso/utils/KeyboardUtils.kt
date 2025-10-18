package com.example.nativeuitesting.espresso.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.platform.app.InstrumentationRegistry

/**
 * Utility functions for keyboard operations.
 */
object KeyboardUtils {

    @JvmStatic
    fun closeKeyboard() {
        Espresso.closeSoftKeyboard()
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
    }

    @JvmStatic
    fun isKeyboardVisible(): Boolean {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return imm.isAcceptingText
    }

    @JvmStatic
    fun waitForKeyboardToHide() {
        var retries = 0
        while (isKeyboardVisible() && retries < 10) {
            Thread.sleep(100)
            retries++
        }
    }

    @JvmStatic
    fun waitForKeyboardToShow() {
        var retries = 0
        while (!isKeyboardVisible() && retries < 10) {
            Thread.sleep(100)
            retries++
        }
    }
}