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
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.kapt")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    group = "org.grida"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}

subprojects {
    dependencies {
        // test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.junit.jupiter:junit-jupiter")
    }

    if (name != "grida-common") {
        dependencies {
            // spring boot
            implementation("org.springframework.boot:spring-boot-starter")

            // properties
            annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

            // core module
            implementation(project(":grida-common"))
        }
    }

    val kotlinMigratedModules = listOf(
        "grida-common",
        "storage-client",
        "ai-client",
        "core-api",
        "database-rds"
    )

    if (kotlinMigratedModules.contains(name)) {
        dependencies {
            // kotlin reflect
            implementation("org.jetbrains.kotlin:kotlin-reflect")

            // kotlin annotation processor
            kapt("org.springframework.boot:spring-boot-configuration-processor")

            // kotlin test
            testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
            testRuntimeOnly("org.junit.platform:junit-platform-launcher")
            testImplementation("io.kotest:kotest-runner-junit5:5.7.2")
            testImplementation("io.kotest:kotest-assertions-core:5.7.2")
        }
    } else {
        dependencies {
            // lombok
            compileOnly("org.projectlombok:lombok")
            annotationProcessor("org.projectlombok:lombok")
        }
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
