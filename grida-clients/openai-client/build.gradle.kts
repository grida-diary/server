dependencies {
    // feign client
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.8")
    implementation("io.github.openfeign:feign-jackson:12.1")

    // jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}

tasks.register("prepareKotlinBuildScriptModel") {}
