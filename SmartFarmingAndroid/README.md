# Smart Farming: Crop and Irrigation Advisor (Android / Java)

Android Studio application written in Java. It records farm details, suggests suitable crops using sample soil/season rules, estimates irrigation after user-entered rainfall, estimates cultivation cost and yield, and saves a text report.

## Open and run
1. Extract this ZIP file.
2. Open Android Studio and choose **Open**.
3. Select the extracted `SmartFarmingAndroid` folder (the folder containing `settings.gradle`).
4. Wait for Gradle Sync. Install Android SDK Platform 35 if Android Studio asks.
5. Select an Android emulator or connect a phone with USB debugging enabled.
6. Press **Run ▶** and select the `app` configuration.

## Requirements
- Android Studio with Android SDK Platform 35
- JDK 17 (Android Studio's bundled JDK is suitable)
- Minimum Android version: Android 6.0 (API 23)

## Features
- Farmer and farm registration
- Soil and season dropdowns
- Crop suggestions: Rice, Wheat, Cotton and Vegetables
- Irrigation water estimate adjusted for entered rainfall
- Sample cost and yield estimates
- Save `FarmReport.txt` to app-private storage

## Java OOP concepts
- Abstraction: abstract `Crop` class
- Inheritance: crop subclasses in `CropTypes.java`
- Polymorphism: overridden `isSuitable()` methods
- Encapsulation: private fields in `Farm` and `Crop`
- Enums: `Soil` and `Season`
- Collections: `List` in `CropAdvisor`
- File handling: `FileOutputStream` in `MainActivity`

## Notes
Crop suitability and water, cost and yield figures are illustrative academic estimates. Actual needs depend on local climate, crop variety, soil tests and irrigation method. The app uses manually entered rainfall; it does not access live weather or sensors. The report is saved in app-private storage.

## GitHub upload
Create an empty GitHub repository, then run these commands from this project folder:
```bash
git init
git add .
git commit -m "Initial Smart Farming Android Java app"
git branch -M main
git remote add origin YOUR_REPOSITORY_URL
git push -u origin main
```
