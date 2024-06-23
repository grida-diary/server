dependencies {
    // spring boot
    implementation("org.springframework.boot:spring-boot-starter")

    // lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // security
    implementation("com.github.wwan13:winter-security:0.0.5")

    // module dependencies
    implementation(project(":grida-core"))
    implementation(project(":grida-apis:apis-core"))
    implementation(project(":grida-domains:domain"))
    runtimeOnly(project(":grida-domains:domain-rds"))
}
