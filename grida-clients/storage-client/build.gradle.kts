dependencies {
    // spring boot
    implementation("org.springframework.boot:spring-boot-starter")

    // aws s3
    implementation("com.amazonaws:aws-java-sdk-s3:1.12.624")

    // test
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("io.kotest:kotest-runner-junit5:5.7.2")
    testImplementation("io.kotest:kotest-assertions-core:5.7.2")
    testImplementation("io.mockk:mockk:1.12.4")

    // core module
    implementation(project(":grida-common"))
}
