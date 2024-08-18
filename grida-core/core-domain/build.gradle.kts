dependencies {
    implementation("org.springframework:spring-tx:6.1.0")

    implementation(project(":grida-clients:openai-client"))
    implementation(project(":grida-clients:storage-client"))
}
