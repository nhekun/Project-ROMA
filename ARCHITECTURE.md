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

## Known Technical Debt

1. **lifecycle-livedata-compose** - Version doesn't exist in 2.7.0; using `lifecycle-viewmodel-compose` which transitively provides `observeAsState`
2. **targetSdk deprecation** - Library modules use deprecated `targetSdk` property
3. **Icon loading** - No caching, reloads on every drawer open
4. **No DI** - ViewModels created via default factory

---

*Architecture as of Stage I completion*