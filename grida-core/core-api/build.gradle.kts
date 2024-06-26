dependencies {
    // module dependencies
    runtimeOnly(project(":grida-database:database-rds"))
    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // security
    implementation("com.github.wwan13:winter-security:0.0.7")
}
