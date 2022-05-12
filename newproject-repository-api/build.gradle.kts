plugins {
    id("io.quarkus") apply(false)
}

dependencies {
    implementation(project(":newproject-domain-models"))
    implementation(project(":newproject-repository"))
}
