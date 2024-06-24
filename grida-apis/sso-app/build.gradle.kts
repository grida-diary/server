dependencies {
    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // security
    implementation("com.github.wwan13:winter-security:0.0.5")

    // module dependencies
    implementation(project(":grida-apis:apis-core"))
    implementation(project(":grida-domains:domain"))
    runtimeOnly(project(":grida-domains:domain-rds"))
}
