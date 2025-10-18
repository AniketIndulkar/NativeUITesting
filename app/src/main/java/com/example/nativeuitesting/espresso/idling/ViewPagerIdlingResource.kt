package com.example.nativeuitesting.espresso.idling

import androidx.test.espresso.IdlingResource
import androidx.viewpager.widget.ViewPager

/**
 * Waits for ViewPager scrolling to complete.
 */
class ViewPagerIdlingResource(
    private val viewPager: ViewPager
) : IdlingResource {

    private var resourceCallback: IdlingResource.ResourceCallback? = null
    private var isIdle = true

    private val listener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
            isIdle = (state == ViewPager.SCROLL_STATE_IDLE)
            if (isIdle) {
                resourceCallback?.onTransitionToIdle()
            }
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
        override fun onPageSelected(position: Int) {}
    }

    init {
        viewPager.addOnPageChangeListener(listener)
    }

    override fun getName(): String = "ViewPagerIdlingResource"

    override fun isIdleNow(): Boolean = isIdle

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.resourceCallback = callback
    }

    fun unregister() {
        viewPager.removeOnPageChangeListener(listener)
    }
}