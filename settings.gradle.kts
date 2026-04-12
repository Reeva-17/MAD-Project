pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
<<<<<<< HEAD
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
=======
>>>>>>> 93ce4a9f96e3ce03963d9c7897397882f106caa0
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

<<<<<<< HEAD
rootProject.name = "TrustLens"
=======
rootProject.name = "MAD FIREBASE"
>>>>>>> 93ce4a9f96e3ce03963d9c7897397882f106caa0
include(":app")
 