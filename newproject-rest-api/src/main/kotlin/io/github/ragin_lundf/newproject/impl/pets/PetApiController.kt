package io.github.ragin_lundf.newproject.impl.pets

import io.github.ragin_lundf.domain_services.pets.PetDomainService
import io.github.ragin_lundf.newproject.dto.pets.models.PetDto
import io.github.ragin_lundf.newproject.pets.PetsApi
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PetApiController(private val petDomainService: PetDomainService): PetsApi {
    override fun createPets(pet: PetDto): PetDto {
        return petDomainService.createPets(pet)
    }

    override fun listPets(limit: Int?): List<PetDto> {
        return petDomainService.listPets()
    }

    override fun showPetById(petId: String): PetDto {
        return petDomainService.getPetById(petId)
    }
}
