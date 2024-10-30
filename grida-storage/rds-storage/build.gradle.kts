allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    // core-module
    compileOnly(project(":grida-core:core-domain"))

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // kotlin jdsl
    implementation("com.linecorp.kotlin-jdsl:jpql-dsl:3.4.2")
    implementation("com.linecorp.kotlin-jdsl:jpql-render:3.4.2")
    implementation("com.linecorp.kotlin-jdsl:hibernate-support:3.4.2")

    // mysql
    runtimeOnly("com.mysql:mysql-connector-j")

    // h2 : test
    testImplementation("com.h2database:h2")
}
