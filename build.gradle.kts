import org.gradle.api.tasks.testing.logging.TestExceptionFormat

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * To learn more about Gradle by exploring our Samples at https://docs.gradle.org/8.8/samples
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    java
    application
    jacoco
    checkstyle
    alias(libs.plugins.spring.boot)
}

group = "hexlet.code"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "hexlet.code.AppApplication"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.bundles.spring.boot)
    implementation(libs.postgresql)

    implementation(libs.mapstruct)
    implementation(libs.jackson.databind.nullable)
    annotationProcessor(libs.mapstruct.processor)

    implementation(libs.datafaker)
    runtimeOnly(libs.h2database)
    testImplementation(libs.bundles.spring.boot.testing)
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        showStandardStreams = true
        events("failed", "skipped", "passed")
    }
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
        html.required = true
    }
}
