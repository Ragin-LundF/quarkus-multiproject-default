pluginManagement {
    val quarkusPluginVersion: String by settings
    val quarkusPluginId: String by settings
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id(quarkusPluginId) version quarkusPluginVersion
    }
}
rootProject.name = "newproject"

include("newproject-domain-models")
include("newproject-domain-services")
include("newproject-dto")
include("newproject-repository")
include("newproject-repository-api")
include("newproject-rest-api")
include("newproject-server")
