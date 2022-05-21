pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Tiktok"
include(":androidApp")
include(":shared")
include(":feature-example")
include(":feature-auth")
