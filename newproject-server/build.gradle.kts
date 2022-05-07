plugins {
    id("io.quarkus")
}

dependencies {
    implementation("io.quarkus:quarkus-config-yaml")
    implementation("io.quarkus:quarkus-micrometer")
    implementation("io.quarkus:quarkus-micrometer-registry-prometheus")
    implementation("io.quarkus:quarkus-opentelemetry")

    implementation(project(":newproject-rest-api"))
}
