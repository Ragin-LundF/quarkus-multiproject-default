package io.github.ragin_lundf.domain_services.pets

import io.github.ragin_lundf.newproject.dto.pets.models.PetDto
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PetDomainService {
    fun getPetById(petId: Long): PetDto {
        return PetDto(
            name = "Jacky",
            photoUrls = emptyList(),
            id = petId,
            category = null,
            tags = null,
            status = PetDto.Status.available
        )
    }
}
