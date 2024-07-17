dependencies {
    compileOnly("jakarta.transaction:jakarta.transaction-api:2.0.1")

    implementation(project(":grida-clients:ai-client"))
    implementation(project(":grida-clients:storage-client"))
}
