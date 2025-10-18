package com.example.nativeuitesting.espresso.assertions


import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.android.material.tabs.TabLayout

/**
 * TabLayout assertions.
 */
object TabAssertions {

    @JvmStatic
    fun assertTabSelected(@IdRes tabLayoutId: Int, position: Int) {
        onView(withId(tabLayoutId)).check { view, _ ->
            val tabLayout = view as TabLayout
            if (tabLayout.selectedTabPosition != position) {
                throw AssertionError("Expected tab $position to be selected, but tab ${tabLayout.selectedTabPosition} is selected")
            }
        }
    }

    @JvmStatic
    fun assertTabCount(@IdRes tabLayoutId: Int, expectedCount: Int) {
        onView(withId(tabLayoutId)).check { view, _ ->
            val tabLayout = view as TabLayout
            if (tabLayout.tabCount != expectedCount) {
                throw AssertionError("Expected $expectedCount tabs, found ${tabLayout.tabCount}")
            }
        }
    }
}