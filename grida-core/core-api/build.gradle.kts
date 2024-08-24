plugins {
    id("com.epages.restdocs-api-spec") version "0.16.0"
}

dependencies {
    // module dependencies
    implementation(project(":grida-core:core-domain"))
    implementation(project(":grida-storage:rds-storage"))
    implementation(project(":grida-clients:kakao-client"))
    implementation(project(":grida-support:logging"))
    implementation(project(":grida-support:monitoring"))

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // kotlin object mapper
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // security
    implementation("com.github.wwan13:winter-security:0.0.10")

    // logging-request
    implementation("com.github.wwan13:spring-request-logger:0.0.7")

    // api docs
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("com.github.wwan13.kotlin-dsl-rest-docs:impl-mockmvc:1.2.9")
}

tasks {
    bootJar {
        enabled = true
    }

    jar {
        enabled = false
    }
}
