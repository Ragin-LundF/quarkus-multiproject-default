import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.openapitools:openapi-generator-gradle-plugin:5.4.0")
    }
}

apply(plugin = "org.openapi.generator")

plugins {
    id("io.quarkus")
}

val ktorVersion = "2.0.1"
dependencies {
    implementation(project(":newproject-dto"))
    implementation(project(":newproject-domain-services"))
    implementation("io.quarkus:quarkus-resteasy")
    implementation("io.quarkus:quarkus-resteasy-jackson")
}

val groupName = "openapi generator"
val baseApiPackage = "io.github.ragin_lundf.newproject"
val apis = listOf(
    mapOf(
        "packageName" to "pets",
        "apiFile" to "openapi-petstore.yml"
    )
)

task("createAllRestApis") {
    group = groupName
}

fun createOpenApiRestTask(openapiFile: String, packageName: String): GenerateTask {
    return tasks.create<GenerateTask>("create-${packageName}-api") {
        group = groupName
        generatorName.set("jaxrs-spec")
        inputSpec.set("${rootDir}/newproject-rest-api/src/main/resources/${openapiFile}")
        outputDir.set("${projectDir}")
        apiPackage.set("${baseApiPackage}.restapi.restgen.${packageName}")
        modelPackage.set("${baseApiPackage}.dto.restgen.${packageName}")
        invokerPackage.set("com")
        globalProperties.set(mapOf(
            "apis" to "",
            "apiDocs" to "false",
            "apiTests" to "false",
            "modelTests" to "false",
            "modelDocs" to "false"

        ))
        configOptions.set(mapOf(
            "interfaceOnly" to "true",
            "dateLibrary" to "java8",
            "useSwaggerAnnotations" to "false"
        ))
        //doFirst {
        //    copy {
        //        from(".openapi-generator-ignore")
        //        into("${packageName}/")
        //    }
        //}
    }
}

fun createOpenApiRestCleanTask(packageName: String): Delete {
    return tasks.create<Delete>("clean-${packageName}-client") {
        group = groupName
        delete("${projectDir}/src/main/java/")
        delete("${projectDir}/src/gen/")
    }
}

apis.forEach { apiDefinition ->
    val createTask = createOpenApiRestTask(
        apiDefinition["apiFile"]!!,
        apiDefinition["packageName"]!!
    )
    val cleanTask = createOpenApiRestCleanTask(
        apiDefinition["packageName"]!!
    )
    tasks.clean {
        dependsOn(cleanTask)
    }
    tasks.named("createAllRestApis") {
        dependsOn(createTask)
    }
    createTask.dependsOn(cleanTask)
}
