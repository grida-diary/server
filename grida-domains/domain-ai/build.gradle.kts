dependencies {
    // spring boot
    implementation("org.springframework.boot:spring-boot-starter")

    // lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // feign client
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.8")
    implementation("io.github.openfeign:feign-jackson:12.1")

    // jackson
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.5")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.5")

    // module dependencies
    compileOnly(project(":grida-domains:domain-image"))
    testImplementation(project(":grida-domains:domain-image"))
    implementation(project(":grida-common"))
}
