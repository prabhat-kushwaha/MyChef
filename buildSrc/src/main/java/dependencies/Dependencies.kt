package dependencies

import Versions

object Dependencies {




    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"

    //ui related libs
    val material = "com.google.android.material:material:${Versions.material}"
    val swipe_refresh_layout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    val constraint_layout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val palette = "androidx.palette:palette:${Versions.palette}"

    //google core lib mainly using it for in-app update
    val play_core_ktx = "com.google.android.play:core-ktx:${Versions.play_core_ktx}"

    //preference library
    val preference = "androidx.preference:preference-ktx:${Versions.preference}"


    //retrofit libs
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val json_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val retrofit_scalars = "com.squareup.retrofit2:converter-scalars:${Versions.retrofit}"

    //room libs
    val room_runtime = "androidx.room:room-runtime:${Versions.room_version}"
    val room_ktx = "androidx.room:room-ktx:${Versions.room_version}"


    //android lifecycle libs
    val lifecycle_view_model =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    val lifecycle_livedata =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    val lifecycle_extensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_extensions}"

    //firebase libs
    val firebase_messaging = "com.google.firebase:firebase-messaging:${Versions.firebase_messaging}"
    val firebase_config = "com.google.firebase:firebase-config-ktx:${Versions.firebase_config}"
    val firebase_analytics =
        "com.google.firebase:firebase-analytics-ktx:${Versions.firebase_analytics}"
    val firebase_crashlytics =
        "com.google.firebase:firebase-crashlytics-ktx:${Versions.firebase_crashlytics}"

    //paging lib
    val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging}"

    //lottie
    val lottie = "com.airbnb.android:lottie:${Versions.lottie}"

    //in-app browser lib
    val browser = "androidx.browser:browser:${Versions.browser}"
    val webkit = "androidx.webkit:webkit:${Versions.webkit}"

    val hilt = "com.google.dagger:hilt-android:${Versions.hilt_version}"
    val hilt_nav = "androidx.hilt:hilt-navigation-fragment:${Versions.hilt_nav}"

    //http loging library
    val http_logger = "com.squareup.okhttp3:logging-interceptor:${Versions.http_logger}"

    val nav_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    val nav_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
    val coil = "io.coil-kt:coil:${Versions.coil}"


}