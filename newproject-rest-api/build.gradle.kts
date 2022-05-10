apply(plugin = "org.openapi.generator")

plugins {
    id("io.quarkus")
}

dependencies {
    implementation(project(":newproject-dto"))
    implementation(project(":newproject-domain-services"))

    implementation("io.quarkus:quarkus-resteasy-jackson")
}

// ---- API Generator task creation
val groupName = "openapi generator"
val baseApiPackage = "io.github.ragin_lundf.newproject"
val apis = listOf(
    mapOf(
        "packageName" to "pets",
        "apiFile" to "openapi-petstore.yml"
    )
)

task("all-rest-api") {
    group = groupName
}

fun createOpenApiRestTask(openapiFile: String, packagePath: String): org.openapitools.generator.gradle.plugin.tasks.GenerateTask {
    return tasks.create<org.openapitools.generator.gradle.plugin.tasks.GenerateTask>("create-${packagePath}-api") {
        group = groupName
        generatorName.set("kotlin-server")
        inputSpec.set("${rootDir}/newproject-rest-api/src/main/resources/${openapiFile}")
        outputDir.set("${projectDir}/gen")
        apiPackage.set("${baseApiPackage}.${packagePath}")
        modelPackage.set("${baseApiPackage}.dto.${packagePath}.models")
        packageName.set("${baseApiPackage}.${packagePath}")
        invokerPackage.set("com")
        modelNameSuffix.set("Dto")
        globalProperties.set(mapOf(
            "apis" to "",
            "supportingFiles" to "",
            "apiDocs" to "false",
            "apiTests" to "false",
            "modelTests" to "false",
            "modelDocs" to "false"

        ))
        configOptions.set(mapOf(
            "interfaceOnly" to "true",
            "dateLibrary" to "java8",
            "useSwaggerAnnotations" to "false",
            "library" to "jaxrs-spec"
        ))
        doFirst {
            copy {
                from(".openapi-generator-ignore")
                into("gen/")
            }
        }
    }
}

fun createOpenApiRestCleanTask(packageName: String): Delete {
    return tasks.create<Delete>("clean-${packageName}-client") {
        group = groupName
        delete("${projectDir}/gen/")
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
    tasks.named("all-rest-api") {
        dependsOn(createTask)
    }
    val ktFormatApiTask = tasks.create<org.jmailen.gradle.kotlinter.tasks.FormatTask>(
        "format-${apiDefinition["packageName"]!!.capitalize()}-api"
    ) {
        group = groupName
        source(files("gen/"))
    }
    createTask.finalizedBy(ktFormatApiTask)
    createTask.dependsOn(cleanTask)
    tasks.named("compileKotlin").get().dependsOn(createTask)
}
