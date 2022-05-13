buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jmailen.gradle:kotlinter-gradle:3.10.0")
        classpath("org.openapitools:openapi-generator-gradle-plugin:5.4.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.6.21")
    }
}

plugins {
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.allopen") version "1.6.21"
    id("java-library")
    id("org.kordamp.gradle.jandex") version "0.12.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
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
    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin-allopen")
    apply(plugin = "org.kordamp.gradle.jandex")
    apply(plugin = "io.spring.dependency-management")

    val quarkusPlatformGroupId: String by project
    val quarkusPlatformArtifactId: String by project
    val quarkusPlatformVersion: String by project

    dependencyManagement {
        dependencies {
            dependency("com.google.code.gson:gson:2.9.0")
            dependency("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.+")

            dependency("org.postgresql:postgresql:42.3.4")

            dependency("org.testcontainers:testcontainers:1.17.3")
            dependency("org.testcontainers:junit-jupiter:1.17.3")
            dependency("org.testcontainers:postgresql:1.17.3")
        }
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
        implementation("io.quarkus:quarkus-logging-json")
        implementation("io.quarkus:quarkus-hibernate-validator")
        implementation("io.quarkus:quarkus-jacoco")
        implementation("io.quarkus:quarkus-kotlin")
        implementation("io.quarkus:quarkus-jackson")
        implementation("io.quarkus:quarkus-liquibase")
        implementation("io.quarkus:quarkus-arc")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
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
        java.srcDir("gen/src/main/kotlin")
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

tasks.wrapper {
    gradleVersion = "7.4.2"
}
