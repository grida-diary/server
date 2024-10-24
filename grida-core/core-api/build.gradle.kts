plugins {
    id("com.epages.restdocs-api-spec") version "0.17.1"
}

dependencies {
    // module dependencies
    implementation(project(":grida-core:core-domain"))
    implementation(project(":grida-storage:rds-storage"))
    implementation(project(":grida-support:logging"))
    implementation(project(":grida-support:monitoring"))
    implementation(project(":grida-clients:feign-config"))
    implementation(project(":grida-clients:kakao-client"))
    implementation(project(":grida-clients:openai-client"))
    implementation(project(":grida-clients:s3-client"))

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // kotlin object mapper
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // security
    implementation("com.github.wwan13:winter-security:1.0.1")

    // logging-request
    implementation("com.github.wwan13:spring-request-logger:1.0.0")

    // api docs
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc:3.0.0")
    testImplementation("com.github.wwan13.kotlin-dsl-rest-docs:api:2.0.1")
    testImplementation("com.github.wwan13.kotlin-dsl-rest-docs:impl-mockmvc:2.0.1")
}

tasks {
    bootJar {
        enabled = true
    }

    jar {
        enabled = false
    }
}
