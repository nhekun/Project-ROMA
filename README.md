# ROMA Launcher

> An experimental Android launcher that presents time and dates using Roman numerals.

**Project status:** Private, experimental, and provided as-is. This project was built with AI assistance. It may be incomplete, unstable, or contain errors; use it at your own risk. See [ABOUT.md](ABOUT.md) for the full notice.

ROMA is a Kotlin/Jetpack Compose launcher prototype exploring a simple idea: make Roman numerals part of the everyday home-screen experience. The current prototype includes a Roman numeral conversion library, a Roman-numeral clock and date, and an app drawer for launching installed apps.

## Project status

This is a personal experiment, not a production-ready launcher. Features and build instructions may change. The repository does not promise ongoing maintenance, support, or compatibility with every Android device. Review the code and permissions before installing or setting it as your default home app.

## Modules

- `app` — Android application entry point and manifest.
- `launcher` — home screen, app drawer, and installed-app handling.
- `ui` — Compose clock, date, and theme components.
- `roman` — platform-independent Roman numeral conversion and formatting.

See [ARCHITECTURE.md](ARCHITECTURE.md) for the module relationships and data flow, and [DOCUMENTATION.md](DOCUMENTATION.md) for further project notes.

## Requirements

- Android Studio with Android SDK Platform 34 installed.
- JDK 21.
- Android device or emulator running Android 10 (API 29) or later to install the app.

The project uses the Gradle wrapper, Android Gradle Plugin 8.13.2, Kotlin 2.0.0, and Jetpack Compose. Use the wrapper rather than installing a separate Gradle version.

## Build and test

From the repository root:

```bash
# Run the Roman conversion unit tests
./gradlew :roman:test

# Build a debug APK
./gradlew :app:assembleDebug
```

On Windows, use `gradlew.bat` instead of `./gradlew`. The debug APK, when successfully built, is under `app/build/outputs/apk/debug/`.

To install on a connected device or emulator:

```bash
./gradlew :app:installDebug
```

Builds have not been independently verified by this document. If a command fails, check that Android SDK Platform 34 and JDK 21 are installed and that `JAVA_HOME` points to the JDK 21 installation.

## Roman numeral behavior

`RomanConverter.toRoman()` supports integers from 0 through 3999: zero is represented as `N`, and positive values use standard subtractive notation with Unicode Roman numeral glyphs. `RomanFormatter` provides convenience formatting functions, including two-digit width padding and reverse conversion for accessibility-related use. Consult the implementation and tests in `roman/` for exact behavior.

## Privacy and permissions

Review `app/src/main/AndroidManifest.xml` and the source before use. The launcher queries installed applications to show their names and icons and to launch them. No privacy or data-handling guarantees are made for this experimental software.

## Contributing

This is a private experimental project. If you have access and want to propose a change, see [CONTRIBUTING.md](CONTRIBUTING.md). Please do not assume that contributions or support requests will be accepted.

## License

No license has been specified. Unless the repository owner adds one, no permission to reuse, distribute, or modify this project should be assumed.
