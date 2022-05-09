apply(plugin = "org.openapi.generator")

plugins {
    id("io.quarkus")
}

dependencies {
    implementation("io.quarkus:quarkus-resteasy")
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

task("all-rest-dto") {
    group = groupName
}

fun createOpenApiRestTask(openapiFile: String, packageName: String): org.openapitools.generator.gradle.plugin.tasks.GenerateTask {
    return tasks.create<org.openapitools.generator.gradle.plugin.tasks.GenerateTask>("create-${packageName}-dto") {
        group = groupName
        generatorName.set("kotlin-server")
        inputSpec.set("${rootDir}/newproject-rest-api/src/main/resources/${openapiFile}")
        outputDir.set("${projectDir}/gen")
        apiPackage.set("${baseApiPackage}.restapi.${packageName}")
        modelPackage.set("${baseApiPackage}.dto.${packageName}.models")
        invokerPackage.set("com")
        modelNameSuffix.set("Dto")
        globalProperties.set(mapOf(
            "models" to "",
            "apiDocs" to "false",
            "apiTests" to "false",
            "modelTests" to "false",
            "modelDocs" to "false"

        ))
        configOptions.set(mapOf(
            "dateLibrary" to "java8",
            "useSwaggerAnnotations" to "false",
            "library" to "jaxrs-spec"
        ))
    }
}

fun createOpenApiRestCleanTask(packageName: String): Delete {
    return tasks.create<Delete>("clean-${packageName}-dto") {
        group = groupName
        delete("${projectDir}/gen")
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
    tasks.named("all-rest-dto") {
        dependsOn(createTask)
    }
    val ktFormatDtoTask = tasks.create<org.jmailen.gradle.kotlinter.tasks.FormatTask>(
        "format-${apiDefinition["packageName"]!!.capitalize()}-dto"
    ) {
        group = groupName
        source(files("gen/"))
    }
    createTask.finalizedBy(ktFormatDtoTask)
    createTask.dependsOn(cleanTask)
    tasks.named("assemble").get().dependsOn(createTask)
}
