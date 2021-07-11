import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {
    // Core and Compose
    private const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val material = "com.google.android.material:material:${Versions.material}"
    private const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    private const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    private const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    private const val composeLiveData =
        "androidx.compose.runtime:runtime-livedata:${Versions.compose}"

    // General
    private const val palette = "androidx.palette:palette-ktx:${Versions.palette}"

    // Accompanist
    private const val accCoil = "com.google.accompanist:accompanist-coil:${Versions.accompanist}"
    private const val accSys =
        "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
    private const val accInsets =
        "com.google.accompanist:accompanist-insets:${Versions.accompanist}"
    private const val accPager = "com.google.accompanist:accompanist-pager:${Versions.accompanist}"
    private const val accPagerInd =
        "com.google.accompanist:accompanist-pager-indicators:${Versions.accompanist}"

    // Retrofit & Moshi
    private const val retrofit = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    private const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"

    // Dagger Hilt
    private const val dagger = "com.google.dagger:hilt-android:${Versions.dagger}"
    private const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.dagger}"
    private const val hiltNav = "androidx.hilt:hilt-navigation-compose:${Versions.hilt}"

    // Room
    private const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    private const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    private const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    // Activity
    private const val activityCompose = "androidx.activity:activity-compose:${Versions.activity}"

    // Navigation
    private const val navCompose = "androidx.navigation:navigation-compose:${Versions.navigation}"

    // Lifecycle
    private const val lifecycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

    // Test
    private const val junit = "junit:junit:${Versions.junit}"
    private const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    private const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    private const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"

    val appLibraries = arrayListOf<String>().apply {
        add(coreKtx)
        add(appCompat)
        add(material)
        add(composeUi)
        add(composeMaterial)
        add(composeUiTooling)
        add(lifecycleRuntimeKtx)
        add(activityCompose)
        add(palette)
        add(accCoil)
        add(navCompose)
        add(retrofit)
        add(moshi)
        add(dagger)
        add(hiltNav)
        add(roomKtx)
        add(roomRuntime)
        add(composeLiveData)
        add(accSys)
        add(accInsets)
        add(accPager)
        add(accPagerInd)
    }

    val compilerLibraries = arrayListOf<String>().apply {
        add(hiltCompiler)
        add(roomCompiler)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJunit)
        add(espressoCore)
        add(composeUiTest)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
    }

    fun DependencyHandler.kapt(list: List<String>) {
        list.forEach { dependency ->
            add("kapt", dependency)
        }
    }

    fun DependencyHandler.implementation(list: List<String>) {
        list.forEach { dependency ->
            add("implementation", dependency)
        }
    }

    fun DependencyHandler.androidTestImplementation(list: List<String>) {
        list.forEach { dependency ->
            add("androidTestImplementation", dependency)
        }
    }

    fun DependencyHandler.testImplementation(list: List<String>) {
        list.forEach { dependency ->
            add("testImplementation", dependency)
        }
    }

}