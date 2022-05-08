plugins {
    id("io.quarkus")
}

dependencies {
    implementation(project(":newproject-domain-models"))
    implementation(project(":newproject-dto"))
}
