plugins {
    id("com.epages.restdocs-api-spec") version "0.16.0"
}

dependencies {
    // module dependencies
    implementation(project(":grida-core:core-domain"))
    implementation(project(":grida-database:database-rds"))

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // kotlin object mapper
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // security
    implementation("com.github.wwan13:winter-security:0.0.8")

    // api docs
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("com.github.wwan13.kotlin-dsl-rest-docs:impl-mockmvc:1.2.1")
}
