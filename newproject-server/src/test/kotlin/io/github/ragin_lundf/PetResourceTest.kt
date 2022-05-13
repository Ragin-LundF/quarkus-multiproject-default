package io.github.ragin_lundf

import io.github.ragin_lundf.newproject.dto.pets.models.PetDto
import io.github.ragin_lundf.testcontainers.PostgresDbResource
import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
@QuarkusTestResource(PostgresDbResource::class)
class PetResourceTest {
    @Test
    fun testPetCreateEndpoint() {
        val pet = PetDto(
            id = 1,
            name = "Jacky"
        )
        given()
            .header("Content-Type", "application/json")
            .body(pet)
            .`when`().post("/pets")
            .then()
            .statusCode(201)
            .body(`is`(pet))
    }

}
