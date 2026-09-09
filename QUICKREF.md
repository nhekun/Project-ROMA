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
| `roman/RomanConverter.kt` | int → Roman (I, IV, XII, MMXXIV) |
| `roman/RomanFormatter.kt` | format(), formatTwoDigit(), formatRange() |
| `ui/ClockViewModel.kt` | LiveData time/date, updates per minute |
| `ui/Clock.kt` | RomanClock (XII:XXIV), RomanDate (XV SEP MMXXIV) |
| `ui/Theme.kt` | RomaTheme (Material3 dark/light) |
| `launcher/HomeScreen.kt` | Main UI: clock + drawer + dock |
| `launcher/AppDrawerViewModel.kt` | Loads installed apps + icons |
| `app/MainActivity.kt` | Entry: HomeScreen() in RomaTheme |

## Commands
```bash
./gradlew.bat :roman:test        # Run tests (PASS)
./gradlew.bat :app:assembleDebug # Build APK (needs dep fix)
./gradlew.bat clean              # Clean build dirs
```

## Current Blockers
- `lifecycle-livedata-compose` version mismatch (use `lifecycle-viewmodel-compose:2.7.0` only)

## GitHub
- Remote: `nhekun/Project-ROMA` (private)
- Push: `git add . && git commit -m "msg" && git push`