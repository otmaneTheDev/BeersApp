pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "BeersApp"
include(":app")
include(":core")
include(":feature_beers:data")
include(":feature_beers:domain")
include(":feature_beers:presentation")
include(":ui_components")
