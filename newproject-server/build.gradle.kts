plugins {
    id("io.quarkus")
}

dependencies {
    implementation("io.quarkus:quarkus-config-yaml")
    implementation("io.quarkus:quarkus-micrometer")
    implementation("io.quarkus:quarkus-micrometer-registry-prometheus")
    implementation("io.quarkus:quarkus-opentelemetry")
    implementation("io.quarkus:quarkus-jdbc-postgresql")

    implementation(project(":newproject-rest-api"))
    implementation(project(":newproject-domain-services"))
    implementation(project(":newproject-repository"))

    testImplementation("org.testcontainers:postgresql")
    testImplementation("org.testcontainers:junit-jupiter")
}
