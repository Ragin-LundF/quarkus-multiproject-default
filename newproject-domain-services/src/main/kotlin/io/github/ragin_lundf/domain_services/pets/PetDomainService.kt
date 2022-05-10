package io.github.ragin_lundf.domain_services.pets

import io.github.ragin_lundf.newproject.dto.pets.models.PetDto
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PetDomainService {
    fun createPets() {
        for (i in 0..10) {
            pets[i.toString()] = PetDto(
                name = "Jacky $i",
                id = i.toLong(),
                tag = "JCK_$i"
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun listPets(): List<PetDto> {
        return pets.values as List<PetDto>
    }

    fun getPetById(petId: String): PetDto {
        return PetDto(
            name = "Jacky",
            id = 1,
            tag = petId
        )
    }

    companion object {
        private val pets = HashMap<String, PetDto>()
    }
}
