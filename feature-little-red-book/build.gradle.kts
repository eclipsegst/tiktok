import Versions.composeVersion
import Versions.navVersion

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    configureAndroidCompose(this)
}

fun Project.configureAndroidCompose(
    commonExtension: com.android.build.api.dsl.CommonExtension<*, *, *, *>,
) {
    commonExtension.apply {
        composeOptions {
            kotlinCompilerExtensionVersion = composeVersion
        }
        buildFeatures {
            compose = true
        }
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.navigation:navigation-compose:$navVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")

    // https://google.github.io/accompanist/
    implementation("com.google.accompanist:accompanist-pager:0.23.1")
    // If using indicators, also depend on
    implementation("com.google.accompanist:accompanist-pager-indicators:0.23.1")

    // https://github.com/onebone/compose-collapsing-toolbar
    implementation("me.onebone:toolbar-compose:2.3.3")
}
