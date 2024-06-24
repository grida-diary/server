dependencies {
    // feign client
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.8")
    implementation("io.github.openfeign:feign-jackson:12.1")

    // jackson
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.5")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.5")
}

tasks.register("prepareKotlinBuildScriptModel") {}
