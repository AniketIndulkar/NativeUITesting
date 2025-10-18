package com.example.nativeuitesting.espresso.assertions

import android.view.Gravity
import androidx.annotation.IdRes
import androidx.drawerlayout.widget.DrawerLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId

/**
 * NavigationDrawer assertions.
 */
object DrawerAssertions {

    @JvmStatic
    fun assertDrawerOpen(@IdRes drawerId: Int) {
        assertDrawerOpen(drawerId, Gravity.START)
    }

    @JvmStatic
    fun assertDrawerOpen(@IdRes drawerId: Int, gravity: Int) {
        onView(withId(drawerId)).check { view, _ ->
            val drawer = view as DrawerLayout
            if (!drawer.isDrawerOpen(gravity)) {
                throw AssertionError("Drawer is not open")
            }
        }
    }

    @JvmStatic
    fun assertDrawerClosed(@IdRes drawerId: Int) {
        assertDrawerClosed(drawerId, Gravity.START)
    }

    @JvmStatic
    fun assertDrawerClosed(@IdRes drawerId: Int, gravity: Int) {
        onView(withId(drawerId)).check { view, _ ->
            val drawer = view as DrawerLayout
            if (drawer.isDrawerOpen(gravity)) {
                throw AssertionError("Drawer is not closed")
            }
        }
    }
}