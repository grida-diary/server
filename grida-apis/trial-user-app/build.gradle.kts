dependencies {
    // spring boot
    implementation("org.springframework.boot:spring-boot-starter")

    // lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // module dependencies
    implementation(project(":grida-common"))
    implementation(project(":grida-apis:apis-core"))
}
