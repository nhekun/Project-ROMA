# ROMA Launcher – Stage I

## Goal

Build the first working version of **ROMA**, an Android launcher whose defining rule is:

> Every number rendered by the launcher is displayed as a Roman numeral.

Keep this MVP intentionally small. Build a solid foundation before adding advanced features.

## Tech Stack

- Kotlin
- Jetpack Compose
- Min SDK 29 (Android 10)
- Target latest stable Android SDK

## Priority Order

### 1. Roman Engine (highest priority)

Create a reusable converter.

Requirements:

- `0 → N`
- `41 → XLI`
- `2026 → MMXXVI`
- Use Unicode Roman numerals (`ⅠⅡⅢⅣⅤⅥⅦⅧⅨⅩⅪⅫ`) where appropriate.
- Include unit tests.

Expose a single formatter:

`RomanFormatter.format()`

Every number shown by the launcher must use this formatter.

### 2. Basic Launcher

Create a launcher that can be set as the default home screen.

Requirements:

- Black background
- White text
- Minimal layout
- Open installed apps
- Swipe up opens the app drawer

### 3. Roman Clock

Display a live clock like:

`Ⅸ:ⅩⅬⅠ`

Requirements:

- Updates every minute
- 12-hour mode
- No Arabic numerals visible

### 4. Roman Date

Display today's date like:

`Ⅵ SEPTEMBER MMXXVI`

- Day in Roman
- Year in Roman
- Month remains English text

## Initial Layout

          Ⅸ:ⅩⅬⅠ

     Ⅵ SEPTEMBER MMXXVI


   [Phone] [Browser] [Camera] [Apps]

## Project Structure

ROMA/
- app/
- launcher/
- roman/
  - RomanConverter.kt
  - RomanFormatter.kt
- ui/
  - HomeScreen.kt
  - Clock.kt
  - Theme.kt

## Out of Scope (Stage I)

Do not implement:

- Lock screen
- Widgets
- Weather
- Notification badges
- Folder renaming
- Imperium Mode
- Custom icon packs
- Settings screen

## Deliverables

- Working APK
- Complete source code
- Roman converter tests
- README explaining the architecture and how the Roman formatting pipeline works.