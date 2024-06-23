dependencies {
    // spring boot
    implementation("org.springframework.boot:spring-boot-starter")

    // lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // aws s3
    implementation("com.amazonaws:aws-java-sdk-s3:1.12.624")

    // core module
    implementation(project(":grida-core"))
}
