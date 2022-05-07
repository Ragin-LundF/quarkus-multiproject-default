buildscript {
    val kotlinVersion = "1.6.20"

    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
        classpath("org.openapitools:openapi-generator-gradle-plugin:5.4.0")
        classpath("org.jmailen.gradle:kotlinter-gradle:3.10.0")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:${kotlinVersion}")
    }
}

plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.allopen") version "1.6.10"
    id("java-library")
}

allprojects {
    repositories {
        mavenCentral()
    }

    group = "io.github.ragin"
    version = "1.0-SNAPSHOT"

}

subprojects {
    apply(plugin = "idea")
    apply(plugin = "java-library")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-allopen")
    apply(plugin = "kotlin-kapt")

    val quarkusPlatformGroupId: String by project
    val quarkusPlatformArtifactId: String by project
    val quarkusPlatformVersion: String by project

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
        implementation("io.quarkus:quarkus-hibernate-reactive")
        implementation("io.quarkus:quarkus-scheduler")
        implementation("io.quarkus:quarkus-logging-json")
        implementation("io.quarkus:quarkus-hibernate-validator")
        implementation("io.quarkus:quarkus-jacoco")
        implementation("io.quarkus:quarkus-kotlin")
        implementation("io.quarkus:quarkus-jackson")
        implementation("io.quarkus:quarkus-liquibase")
        implementation("io.quarkus:quarkus-arc")
        testImplementation("io.quarkus:quarkus-junit5")
        testImplementation("io.rest-assured:rest-assured")
    }

    allOpen {
        annotation("javax.ws.rs.Path")
        annotation("javax.enterprise.context.ApplicationScoped")
        annotation("io.quarkus.test.junit.QuarkusTest")
    }
}

// -------- Java/Kotlin configuration
subprojects {
    sourceSets.getByName("main") {
        java.srcDir("src/main/java")
        java.srcDir("src/main/kotlin")
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_16.toString()
        kotlinOptions.javaParameters = true
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_16
        targetCompatibility = JavaVersion.VERSION_16
    }
}

