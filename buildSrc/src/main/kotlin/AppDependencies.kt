import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {
    // Android UI
    private const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val material = "com.google.android.material:material:${Versions.material}"
    private const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    private const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    private const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"

    // Lifecycle
    private const val lifecycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

    // Activity
    private const val activityCompose = "androidx.activity:activity-compose:${Versions.lifecycle}"

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
    }

    val annotationLibraries = arrayListOf<String>().apply {

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