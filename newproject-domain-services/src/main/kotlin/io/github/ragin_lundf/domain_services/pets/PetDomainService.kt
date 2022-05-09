package io.github.ragin_lundf.domain_services.pets

import io.github.ragin_lundf.newproject.dto.pets.models.PetDto
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PetDomainService {
    fun getPetById(petId: String): PetDto {
        return PetDto(
            name = "Jacky",
            id = 1,
            tag = petId
        )
    }
}
