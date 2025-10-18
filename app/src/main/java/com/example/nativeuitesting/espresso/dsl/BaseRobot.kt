package com.example.nativeuitesting.espresso.dsl

/**
 * Base class for all Robot classes.
 * Provides DSL-style access to all framework actions and assertions.
 *
 * Robot classes should extend this and implement component-specific behavior.
 *
 * Example:
 * ```
 * class MyComponentRobot : BaseRobot() {
 *     fun clickButton() {
 *         clickOn(R.id.button)  // Direct access, no qualification!
 *     }
 * }
 * ```
 */
abstract class BaseRobot : EspressoTestContext()