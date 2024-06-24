dependencies {
    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // mysql
    implementation("com.mysql:mysql-connector-j")

    // H2 for test
    testImplementation("com.h2database:h2")

    // module dependencies
    compileOnly(project(":grida-domains:domain"))
    testImplementation(project(":grida-domains:domain"))
}
