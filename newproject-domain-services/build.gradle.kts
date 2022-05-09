plugins {
    id("io.quarkus")
}

dependencies {
    implementation(project(":newproject-domain-models"))
    implementation(project(":newproject-dto"))

    implementation("io.quarkus:quarkus-hibernate-orm")
    implementation("io.quarkus:quarkus-scheduler")
}
