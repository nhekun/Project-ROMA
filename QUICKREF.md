# ROMA Launcher - Quick Reference

## One-Liner
**Android launcher where every number = Roman numerals**

## Module Map
```
app (entry)
  └─ launcher (HomeScreen, AppDrawer)
       ├─ ui (Clock, Theme)
       │    └─ roman (conversion logic)
       └─ roman
```

## Key Files

| File | Purpose |
|------|---------|
| `roman/RomanConverter.kt` | Core conversion algorithm (int → Roman) |
| `roman/RomanFormatter.kt` | Public API: format(), formatTwoDigit(), formatRange(), toArabic() |
| `ui/ClockViewModel.kt` | LiveData time/date, updates per minute |
| `ui/Clock.kt` | RomanClock (XII:XXIV), RomanDate (XV SEP MMXXIV) + a11y |
| `ui/Theme.kt` | RomaTheme (Material3 dark/light) + **Serif typography** |
| `launcher/HomeScreen.kt` | Main UI: clock + drawer + dock |
| `launcher/AppDrawerViewModel.kt` | Loads installed apps + icons |
| `app/MainActivity.kt` | Entry: HomeScreen() in RomaTheme |

## Commands
```bash
./gradlew.bat :roman:test        # Run tests (PASS)
./gradlew.bat :app:assembleDebug # Build APK (needs JDK 21)
./gradlew.bat clean              # Clean build dirs
```

## Visual Design (Stage I Polish)
- **Clock**: Serif bold, 72sp, letter-spacing 0.5sp — "luxury watch" feel
- **Date**: Serif medium, 24sp, letter-spacing 0.3sp
- **App labels**: Sans-serif medium, 12sp, letter-spacing 0.2sp
- **Icons**: Monochrome white tint on pure black (#000000)
- **Drawer header**: Serif medium, "APPS"

## Fixed Issues (Stage I)
- ✅ `lifecycle-livedata-compose` dependency cleanup (BOM-managed versions)
- ✅ Icon caching by package name in AppIcon
- ✅ Accessibility content descriptions for Roman numerals
- ✅ Adaptive app drawer grid (GridCells.Adaptive)
- ✅ Removed deprecated targetSdk from library modules
- ✅ formatRange() and toArabic() added to RomanFormatter
- ✅ **Serif typography system** for classical Roman aesthetic
- ✅ **Monochrome icon tint** (white on black)

