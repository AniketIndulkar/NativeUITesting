package com.example.nativeuitesting.espresso.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider

/**
 * Utility functions for accessing resources in tests.
 */
object ResourceUtils {

    private val context: Context
        get() = ApplicationProvider.getApplicationContext()

    @JvmStatic
    fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    @JvmStatic
    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }

    @JvmStatic
    fun getColor(@ColorRes resId: Int): Int {
        return ContextCompat.getColor(context, resId)
    }

    @JvmStatic
    fun getDrawable(@DrawableRes resId: Int): Drawable? {
        return ContextCompat.getDrawable(context, resId)
    }

    @JvmStatic
    fun getQuantityString(resId: Int, quantity: Int): String {
        return context.resources.getQuantityString(resId, quantity, quantity)
    }

    @JvmStatic
    fun getDimension(resId: Int): Float {
        return context.resources.getDimension(resId)
    }

    @JvmStatic
    fun getDimensionPixelSize(resId: Int): Int {
        return context.resources.getDimensionPixelSize(resId)
    }

    @JvmStatic
    fun getResourceName(resId: Int): String {
        return context.resources.getResourceName(resId)
    }
}