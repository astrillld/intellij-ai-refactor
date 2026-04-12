plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.17.4"
}

group = "com.astrild"
version = "0.1.0"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

intellij {
    version.set("2024.1")
    type.set("IC")
    plugins.set(listOf("java"))
}

tasks {
    patchPluginXml {
        sinceBuild.set("241")
        untilBuild.set("241.*")
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(17)
    }
}