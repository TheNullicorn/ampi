rootProject.name = "ampi"

pluginManagement {
    plugins {
        val kotlinVersion = settings.extra["kotlin.version"] as String

        kotlin("jvm") version kotlinVersion
    }
}
