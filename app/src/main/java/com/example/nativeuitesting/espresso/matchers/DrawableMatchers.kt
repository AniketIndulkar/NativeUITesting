package com.example.nativeuitesting.espresso.matchers

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

/**
 * Drawable-specific matchers.
 */
object DrawableMatchers {

    @JvmStatic
    fun withDrawable(@DrawableRes resourceId: Int): Matcher<View> {
        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with drawable from resource id: $resourceId")
            }

            override fun matchesSafely(imageView: ImageView): Boolean {
                if (resourceId == 0) {
                    return imageView.drawable == null
                }

                val expectedDrawable = imageView.context.getDrawable(resourceId) ?: return false
                val actualDrawable = imageView.drawable ?: return false

                // Compare using constant state
                if (expectedDrawable.constantState != null && actualDrawable.constantState != null) {
                    return expectedDrawable.constantState == actualDrawable.constantState
                }

                // Fallback: compare bitmap
                return getBitmap(expectedDrawable).sameAs(getBitmap(actualDrawable))
            }

            private fun getBitmap(drawable: Drawable): Bitmap {
                if (drawable is BitmapDrawable) {
                    return drawable.bitmap
                }

                val bitmap = Bitmap.createBitmap(
                    drawable.intrinsicWidth.coerceAtLeast(1),
                    drawable.intrinsicHeight.coerceAtLeast(1),
                    Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(bitmap)
                drawable.setBounds(0, 0, canvas.width, canvas.height)
                drawable.draw(canvas)
                return bitmap
            }
        }
    }

    @JvmStatic
    fun hasDrawable(): Matcher<View> {
        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has drawable (not null)")
            }

            override fun matchesSafely(imageView: ImageView): Boolean {
                return imageView.drawable != null
            }
        }
    }

    @JvmStatic
    fun hasNoDrawable(): Matcher<View> {
        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has no drawable (null)")
            }

            override fun matchesSafely(imageView: ImageView): Boolean {
                return imageView.drawable == null
            }
        }
    }
}