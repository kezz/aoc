import org.jetbrains.kotlin.gradle.dsl.ExplicitApiMode

plugins {
    kotlin("jvm") version "1.7.22"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.arrow-kt:arrow-core:1.1.4-alpha.20")
}

tasks {
    wrapper {
        gradleVersion = "7.6"
    }
}

kotlin {
    explicitApi = ExplicitApiMode.Strict
}
