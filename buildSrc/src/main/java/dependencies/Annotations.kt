package dependencies

import Versions

object Annotations {
    val dataBinding = "com.android.databinding:compiler:${Versions.data_binding}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room_version}"
    val hilt_compiler="com.google.dagger:hilt-compiler:${Versions.hilt_version}"
}