# ROMA Launcher - Architecture Reference

## Data Flow

```
ClockViewModel (ui)
    ├─ time: LiveData<String>  → "XII:XXIV"
    └─ date: LiveData<String>  → "XV SEPTEMBER MMXXIV"
           │
           ▼
    observeAsState() in Compose
           │
           ▼
RomanClock / RomanDate (ui/Clock.kt)
           │
           ▼
HomeScreen (launcher) → displays both
```

## Roman Numeral Conversion

```
RomanConverter.toRoman(int) → String
    ├─ 1-3999 supported
    ├─ Standard subtractive notation (IV, IX, XL, XC, CD, CM)
    └─ Used by RomanFormatter.format()

RomanFormatter.formatTwoDigit(int) → String
    └─ Zero-pads: 3 → "III", 12 → "XII"

RomanFormatter.formatRange(int, int) → String
    └─ Range format: 1, 10 → "I-X"

RomanFormatter.toArabic(String) → Int
    └─ Reverse conversion for accessibility: "X" → 10
```

## UI Component Tree

```
RomaTheme (Theme.kt)
└─ HomeScreen (HomeScreen.kt)
   ├─ RomanClock (Clock.kt)
   ├─ RomanDate (Clock.kt)
   ├─ AppDrawer (HomeScreen.kt)
   │   └─ LazyHorizontalGrid → AppIcon[]
   └─ BottomDock (HomeScreen.kt)
       └─ Row → 4x AppIcon
```

## Dependency Graph (build.gradle.kts)

```kotlin
// roman/build.gradle.kts
// NO Android deps - pure Kotlin

// ui/build.gradle.kts
implementation(":roman")
implementation("androidx.compose.material3:material3:1.2.0")
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

// launcher/build.gradle.kts
implementation(":roman")
implementation(":ui")
// Same Compose/Lifecycle deps

// app/build.gradle.kts
implementation(":launcher")
implementation(":ui")
implementation("androidx.activity:activity-compose:1.8.2")
```

## Key Classes

| Class | Module | Responsibility |
|-------|--------|----------------|
| `RomanConverter` | roman | Core algorithm |
| `RomanFormatter` | roman | Public API, formatting variants |
| `ClockViewModel` | ui | Time/date state, formatting |
| `RomanClock` | ui | Composable: time display |
| `RomanDate` | ui | Composable: date display |
| `RomaTheme` | ui | Material3 theming |
| `HomeScreen` | launcher | Root composable, gestures |
| `AppDrawerViewModel` | launcher | App list + icons |
| `AppModel` | launcher | Data: package, label, icon |
| `MainActivity` | app | Entry point |

## Build Variants

| Variant | Modules Built |
|---------|---------------|
| `debug` | All 4 modules |
| `release` | All 4 modules (minified) |
| `test` | roman only (unit tests) |

## Testing

```bash
# Unit tests (roman only - pure Kotlin)
./gradlew.bat :roman:test

# Future: Instrumented tests
./gradlew.bat :app:connectedDebugAndroidTest
./gradlew.bat :ui:connectedDebugAndroidTest
./gradlew.bat :launcher:connectedDebugAndroidTest
```

## Known Technical Debt (Remaining)

1. **No DI** - ViewModels created via default factory (acceptable for Stage I scope)
2. **JDK 25 incompatibility** - Build requires JDK 21; Gradle 8.7/Kotlin 1.9.24 don't support JDK 25

## Resolved in Stage I

- ✅ **lifecycle-livedata-compose** - Cleaned up dependencies; using Compose BOM for version management
- ✅ **targetSdk deprecation** - Removed from library modules (ui, launcher)
- ✅ **Icon loading** - Added caching by package name in AppIcon composable
- ✅ **Accessibility** - Added content descriptions with spoken-number conversion
- ✅ **Adaptive grid** - App drawer uses GridCells.Adaptive instead of hardcoded 4 rows

---

*Architecture as of Stage I completion*