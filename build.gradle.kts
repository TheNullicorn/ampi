plugins {
    kotlin("jvm")
    id("application")
}

group = "me.nullicorn"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

application {
    mainClass.set("me.nullicorn.ampi.AppKt")
}

tasks {
    val fatJar by registering(Jar::class) {
        group = "build"
        dependsOn("compileKotlin", "compileJava", "processResources")

        archiveClassifier.set("client-app")
        manifest {
            attributes("Main-Class" to application.mainClass)
        }

        val sourcesMain = sourceSets.main.get()
        from(
            sourcesMain.output,
            configurations.runtimeClasspath.get().map {
                if (it.isDirectory) it else zipTree(it)
            }
        )
    }

    getByName("build").finalizedBy(fatJar)
}