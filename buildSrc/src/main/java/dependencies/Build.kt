package dependencies


object Build {
   val gradle_dependencies = "com.android.tools.build:gradle:${Versions.gradle}"
   val kotlin_gradle_plugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
   val google_service = "com.google.gms:google-services:${Versions.google_service}"
   val firebase_crashlytics =
        "com.google.firebase:firebase-crashlytics-gradle:${Versions.crashlytics}"
   val hilt_android = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt_version}"


    val nav_safe_arg = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.nav_version}"
}