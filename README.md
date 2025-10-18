com.mobile.core.uitesting.espresso/
│
├── actions/                                    # ALL UI ACTIONS
│   ├── ClickActions.kt                        # Click, double-click, long-click
│   ├── TextActions.kt                         # Type, replace, clear text
│   ├── ScrollActions.kt                       # Scroll to view
│   ├── SwipeActions.kt                        # Swipe up/down/left/right
│   ├── GeneralActions.kt                      # Back, keyboard, switch
│   ├── WaitActions.kt                         # ✅ MOVED HERE - Wait for views
│   └── specialized/
│       ├── RecyclerActions.kt                 # RecyclerView interactions
│       ├── DrawerActions.kt                   # NavigationDrawer
│       ├── TabActions.kt                      # TabLayout
│       ├── ViewPagerActions.kt                # ViewPager/ViewPager2
│       ├── PickerActions.kt                   # Date/Time/Number pickers
│       ├── ChipActions.kt                     # Material Chips
│       ├── SliderActions.kt                   # SeekBar/Slider
│       ├── BottomSheetActions.kt              # BottomSheet behavior
│       ├── CompoundDrawableActions.kt         # Click drawables in TextView
│       └── SnackbarActions.kt                 # Snackbar interactions
│
├── assertions/                                 # ALL ASSERTIONS
│   ├── ViewAssertions.kt                      # Display, enabled, clickable
│   ├── TextAssertions.kt                      # Text content checks
│   ├── VisibilityAssertions.kt                # Visibility states
│   ├── StateAssertions.kt                     # Checked, selected, focused
│   ├── HierarchyAssertions.kt                 # Parent, child, sibling
│   └── specialized/
│       ├── RecyclerAssertions.kt              # RecyclerView assertions
│       ├── DrawerAssertions.kt                # Drawer state checks
│       ├── TabAssertions.kt                   # Tab selection checks
│       └── SnackbarAssertions.kt              # Snackbar displayed checks
│
├── matchers/                                   # ALL CUSTOM MATCHERS
│   ├── ViewMatchers.kt                        # Basic view matchers
│   ├── TextMatchers.kt                        # Text-specific matchers
│   ├── HierarchyMatchers.kt                   # Parent/child/sibling
│   ├── LayoutMatchers.kt                      # Size, position, orientation
│   ├── DrawableMatchers.kt                    # Drawable/image matching
│   ├── ColorMatchers.kt                       # Color matching
│   └── specialized/
│       ├── RecyclerMatchers.kt                # RecyclerView matchers
│       └── CompoundMatchers.kt                # Complex combined matchers
│
├── builders/                                   # FLUENT BUILDERS
│   ├── ViewBuilder.kt                         # Fluent view matcher builder
│   └── MatcherBuilder.kt                      # Advanced matcher composition
│
├── idling/                                     # IDLING RESOURCES
│   ├── IdlingResourceManager.kt               # Manage idling resources
│   ├── ViewIdlingResource.kt                  # Wait for view to appear
│   ├── ViewGoneIdlingResource.kt              # Wait for view to disappear
│   ├── ViewStateIdlingResource.kt             # Wait for view state
│   ├── ViewPagerIdlingResource.kt             # ViewPager scrolling
│   ├── RecyclerIdlingResource.kt              # RecyclerView animations
│   ├── DrawerIdlingResource.kt                # Drawer open/close
│   └── NetworkIdlingResource.kt               # Network requests (optional)
│
├── rules/                                      # TEST RULES
│   ├── DisableAnimationsRule.kt               # Disable animations
│   ├── ScreenshotOnFailureRule.kt             # Capture failures
│   ├── AsyncSchedulerRule.kt                  # RxJava/Coroutines sync
│   ├── GrantPermissionsRule.kt                # Auto-grant permissions
│   └── RetryRule.kt                           # Retry flaky tests
│
├── screens/                                    # ROBOT/SCREEN PATTERN
│   ├── BaseScreen.kt                          # Base screen class
│   └── ScreenFactory.kt                       # Screen creation helper
│
├── extensions/                                 # KOTLIN EXTENSIONS
│   ├── ViewInteractionExtensions.kt           # Extension functions for ViewInteraction
│   ├── ViewMatcherExtensions.kt               # Extension functions for matchers
│   └── TestExtensions.kt                      # General test helpers
│
├── utils/                                      # UTILITIES
│   ├── ResourceUtils.kt                       # Get strings, drawables, etc.
│   ├── OrientationUtils.kt                    # Screen rotation
│   ├── PermissionUtils.kt                     # Permission handling
│   ├── KeyboardUtils.kt                       # Keyboard visibility
│   └── DeviceUtils.kt                         # Device info, screenshots
│
└── config/                                     # CONFIGURATION
├── TestConfig.kt                          # Global test configuration
└── TimeoutConfig.kt                       # Timeout constants
```

---

## 📝 REASON: Why WaitActions Should Be With Actions

You're **100% CORRECT**! Here's why:

### ❌ Old Structure (Separated)
```
├── actions/
│   └── ClickActions.kt
├── wait/
│   └── WaitActions.kt      # ❌ Separated - inconsistent!
```

### ✅ New Structure (Together)
```
├── actions/
│   ├── ClickActions.kt
│   ├── WaitActions.kt      # ✅ Together - consistent!
│   └── ...