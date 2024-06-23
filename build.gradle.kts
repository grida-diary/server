plugins {
    id("org.springframework.boot") version "2.7.13"
    id("io.spring.dependency-management") version "1.1.5"
    id("java")
    kotlin("jvm") version "1.9.24"
    kotlin("kapt") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
    kotlin("plugin.jpa") version "1.9.24"
    kotlin("plugin.lombok") version "1.9.24"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    group = "org.grida"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.kapt")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    dependencies {
        // configuration
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

        // test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.junit.jupiter:junit-jupiter")
    }

    tasks {
        test {
            useJUnitPlatform()
        }

        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "17"
            }
        }

        compileTestKotlin {
            kotlinOptions {
                jvmTarget = "17"
            }
        }

        bootJar {
            enabled = false
        }

        jar {
            enabled = true
        }
    }
}
