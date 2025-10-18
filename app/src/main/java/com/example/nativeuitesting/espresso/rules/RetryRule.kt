package com.example.nativeuitesting.espresso.rules

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Retries flaky tests automatically.
 *
 * Usage:
 * ```
 * @get:Rule
 * val retryRule = RetryRule(retryCount = 3)
 * ```
 */
class RetryRule(private val retryCount: Int = 3) : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                var lastException: Throwable? = null

                for (i in 0..retryCount) {
                    try {
                        base.evaluate()
                        return // Test passed
                    } catch (t: Throwable) {
                        lastException = t
                        if (i < retryCount) {
                            println("Test ${description.displayName} failed (attempt ${i + 1}/$retryCount). Retrying...")
                            Thread.sleep(1000) // Wait before retry
                        }
                    }
                }

                println("Test ${description.displayName} failed after $retryCount retries")
                throw lastException!!
            }
        }
    }
}