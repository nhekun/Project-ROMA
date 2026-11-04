# Contributing

Thanks for your interest in ROMA Launcher. This is a private experiment and contributions are not guaranteed to be reviewed, merged, or supported. Please coordinate with the repository owner before investing effort in a change.

## Before making a change

- Keep the prototype's scope and modular structure in mind (`app`, `launcher`, `ui`, and `roman`).
- Prefer focused changes that are easy to review.
- Do not commit credentials, signing keys, local SDK paths, personal data, or generated build output.
- For a behavior change, update or add tests where practical. Roman numeral conversion belongs in the platform-independent `roman` module.

## Validate changes

Use JDK 21 and Android SDK Platform 34, then run relevant checks from the repository root:

```bash
./gradlew :roman:test
./gradlew :app:assembleDebug
```

Use `gradlew.bat` on Windows. Include the commands you ran and their results in your pull request or change description. If the build could not be run, say so plainly rather than implying it passed.

## Pull requests

Describe the problem, the approach, and any user-visible or compatibility impact. Include screenshots for visual changes when useful. Keep in mind that this is a private experimental project built with AI assistance and is used at the contributor's own risk.
