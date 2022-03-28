import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "de.herrlau"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    testApi("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("org.assertj:assertj-core:3.22.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}