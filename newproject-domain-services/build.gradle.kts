plugins {
    id("io.quarkus") apply(false)
}

dependencies {
    implementation(project(":newproject-domain-models"))
    implementation(project(":newproject-dto"))
    implementation(project(":newproject-repository-api"))

    implementation("io.quarkus:quarkus-scheduler")
}
