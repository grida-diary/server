dependencies {
    // feign client
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.0.0")

    // jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
}

tasks.register("prepareKotlinBuildScriptModel") {}
