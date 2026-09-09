# ROMA Launcher - Project Documentation

## Core Idea

**ROMA Launcher** is an Android launcher where **every number rendered on screen is displayed as Roman numerals**. This includes:
- Clock time (hours:minutes)
- Date (day, month, year)
- App counts, notification badges, settings values
- Any numeric UI element

The project follows a modular architecture with 4 Gradle modules.

---

## Project Structure

```
Project ROMA/
├── roman/          # Pure Kotlin library - Roman numeral conversion
├── ui/             # Android library - Compose UI components
├── launcher/       # Android library - Launcher screens & logic
├── app/            # Android application - Entry point
├── build.gradle.kts
├── settings.gradle.kts
└── gradle.properties
```

### Module Dependencies
```
app → launcher → ui → roman
         ↘
          ui → roman
```

---

## Module Details

### 1. `roman` (Pure Kotlin Library)
**Purpose**: Core Roman numeral conversion logic - no Android dependencies.

**Files**:
- `RomanConverter.kt` - Core conversion algorithms (int → Roman numeral string)
- `RomanFormatter.kt` - High-level formatting API (`format()`, `formatTwoDigit()`, `formatRange()`)
- `RomanConverterTest.kt` - Unit tests (all passing)

**Key Features**:
- Converts integers 1-3999 to Roman numerals
- Special handling for clock formatting (two-digit zero-padded)
- Range formatting (e.g., "I-X")

---

### 2. `ui` (Android Library - Compose)
**Purpose**: Reusable Compose UI components using Roman numerals.

**Files**:
- `ClockViewModel.kt` - ViewModel updating time/date every minute, formats via `RomanFormatter`
- `Clock.kt` - Composable components:
  - `RomanClock` - Displays time as "XII:XXIV" (12-hour format)
  - `RomanDate` - Displays date as "XV SEPTEMBER MMXXIV"
- `Theme.kt` - Material3 theme (`RomaTheme`) with dark/light color schemes

**Dependencies**: `roman`, Compose Material3, Lifecycle ViewModel Compose

---

### 3. `launcher` (Android Library - Launcher Logic)
**Purpose**: Main launcher screens and app management.

**Files**:
- `HomeScreen.kt` - Main launcher UI:
  - Top: `RomanClock` + `RomanDate`
  - Center: App drawer (horizontal grid, swipeable)
  - Bottom: Favorite apps dock (4 slots)
  - Swipe gestures for drawer open/close
- `AppDrawerViewModel.kt` - Manages installed app list, loading icons
- `AppModel.kt` - Data class for app info (package, label, icon)

**Dependencies**: `roman`, `ui`, Compose Material3, Lifecycle

---

### 4. `app` (Android Application)
**Purpose**: Entry point, minimal wrapper.

**Files**:
- `MainActivity.kt` - Sets content to `HomeScreen()` inside `RomaTheme`
- `RomaApplication.kt` - Application class (placeholder)
- `AndroidManifest.xml` - Launcher intent filters, permissions
- Resources: icons, strings, themes

---

## Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Kotlin 1.9+ |
| Build | Gradle 8.5+ (Kotlin DSL) |
| UI | Jetpack Compose 1.6 / Material3 |
| Architecture | MVVM (ViewModel + LiveData) |
| Min SDK | 29 (Android 10) |
| Target SDK | 34 |
| JDK | 21 |

---

## Implemented Features (Stage I)

✅ **Roman numeral conversion** - Core logic complete, tested
✅ **Clock display** - Hours:minutes in Roman numerals (12-hr format)
✅ **Date display** - Day (Roman), Month (English), Year (Roman)
✅ **Auto-update** - ClockViewModel updates every minute
✅ **Home screen** - Clock + date + app drawer + bottom dock
✅ **App drawer** - Horizontal grid, swipe to open/close
✅ **App icons** - Loaded via PackageManager
✅ **Theme** - Dark/light Material3 theme
✅ **Multi-module build** - 4 modules, proper dependency graph

---

## Build & Run

### Prerequisites
- JDK 21 (`JAVA_HOME` set)
- Android SDK (API 34, Build Tools 34.0.0)
- `local.properties` with `sdk.dir` path

### Commands
```bash
# Run tests (roman module)
./gradlew.bat :roman:test

# Build debug APK
./gradlew.bat :app:assembleDebug

# Clean build
./gradlew.bat clean

# Install on device
./gradlew.bat :app:installDebug
```

### Known Build Issue
`androidx.lifecycle:lifecycle-livedata-compose` artifact versioning mismatch. Currently using `lifecycle-viewmodel-compose:2.7.0` which includes observeAsState. The separate `lifecycle-livedata-compose` artifact doesn't exist in 2.6.x/2.7.x versions.

---

## Git / GitHub

- **Local**: `D:\Tech Stuff\Project ROMA\Project ROMA`
- **Remote**: `https://github.com/nhekun/Project-ROMA` (private)
- **Branch**: `main` → tracks `origin/main`
- **Ignore**: `.gradle/`, `*/build/`, `local.properties`, `cmdline-tools.zip`

---

## Next Steps (Stage II+)

- [ ] Fix `lifecycle-livedata-compose` dependency (use BOM or correct version)
- [ ] Complete app drawer functionality (search, categories)
- [ ] Add settings screen (all numbers in Roman)
- [ ] Notification badges in Roman numerals
- [ ] Widget support
- [ ] Performance optimization (icon caching)
- [ ] Landscape/tablet layouts
- [ ] Accessibility (content descriptions for Roman numerals)

---

## File Inventory (Source Only)

| Module | Kotlin Files | Resources |
|--------|-------------|-----------|
| roman | 3 (2 src + 1 test) | - |
| ui | 3 | - |
| launcher | 3 | - |
| app | 2 | Manifest, icons, strings, themes |
| **Total** | **11** | **~15 resource files** |

---

*Generated: 2026-09-09*
*Project: ROMA Launcher - Stage I*