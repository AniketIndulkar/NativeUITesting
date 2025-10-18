package com.example.nativeuitesting.espresso.idling

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource

/**
 * Helper for managing IdlingResources with automatic cleanup.
 */
object IdlingResourceManager {

    @JvmStatic
    fun <T> withResource(resource: IdlingResource, block: () -> T): T {
        IdlingRegistry.getInstance().register(resource)
        try {
            return block()
        } finally {
            IdlingRegistry.getInstance().unregister(resource)
        }
    }

    @JvmStatic
    fun <T> withResources(vararg resources: IdlingResource, block: () -> T): T {
        resources.forEach { IdlingRegistry.getInstance().register(it) }
        try {
            return block()
        } finally {
            resources.forEach { IdlingRegistry.getInstance().unregister(it) }
        }
    }

    @JvmStatic
    fun register(resource: IdlingResource) {
        IdlingRegistry.getInstance().register(resource)
    }

    @JvmStatic
    fun unregister(resource: IdlingResource) {
        IdlingRegistry.getInstance().unregister(resource)
    }

    @JvmStatic
    fun unregisterAll() {
        IdlingRegistry.getInstance().resources.forEach { resource ->
            IdlingRegistry.getInstance().unregister(resource)
        }
    }
}