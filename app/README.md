# 🎓 GPA Calculator App

A beautifully designed Android application to calculate and track your GPA (Grade Point Average) across terms. Built with Kotlin and Jetpack libraries, the app helps students manage their academic progress with features like GPA history tracking, performance trends, and customizable settings.

## ✨ Features

- 📱 **Simple GPA Calculator** – Add subjects, credits, and grades easily
- 📈 **Term-wise GPA History** – Automatically saves and displays historical GPA entries
- 📊 **Trend Indicator** – Shows whether your GPA is improving, dropping, or stable
- ⚙️ **Settings Page** – Customize app settings and manage data
- 💾 **Local Storage** – Uses `SharedPreferences` for offline GPA data persistence

## 🧱 Tech Stack

- **Language**: Kotlin
- **Architecture**: MVVM (lightweight)
- **UI**: Android Views (Material Design 3 styled)
- **Data Storage**: SharedPreferences + Gson

## 📸 Screenshots

> _Add screenshots here of your Home, Calculator, and History screens_  
> _You can use Android Studio Emulator or your phone to take screenshots._

## 🚀 Getting Started

1. Clone the repo:

   ```bash
   git clone https://github.com/Rusith21/GPA-Calculator-App.git
   ```

2. Open the project in **Android Studio**
3. Sync Gradle and run on a device or emulator

## 🛠️ Building the APK

To generate the APK:

```bash
Build > Build Bundle(s) / APK(s) > Build APK(s)
```

Then locate the APK in:
```
/app/build/outputs/apk/debug/app-debug.apk
```

## 📁 Folder Structure

```
📂 app/
 ┣ 📂 java/com/example/gpacalculatorapp/
 ┃ ┣ 📄 HomeActivity.kt
 ┃ ┣ 📄 CalculatorActivity.kt
 ┃ ┣ 📄 HistoryActivity.kt
 ┃ ┣ 📂 util/
 ┃ ┃ ┗ 📄 GPAHistoryManager.kt
 ┣ 📂 res/layout/
 ┃ ┗ 📄 activity_home.xml, activity_calculator.xml, activity_history.xml
 ┣ 📂 res/drawable/
 ┃ ┗ 📄 ic_trending_up.xml, ic_trending_down.xml, ic_trending_flat.xml
```

## 📜 License

This project is licensed under the MIT License.  
Feel free to use, modify, and share with credit!

---

> Created with ❤️ by [Rusith Wijesinghe](https://github.com/Rusith21)