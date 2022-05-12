package io.github.ragin_lundf.domain_services.pets

import io.github.ragin_lundf.domain_models.pets.PetModel
import io.github.ragin_lundf.newproject.dto.pets.models.PetDto
import io.github.ragin_lundf.repository.api.PetRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PetDomainService(
    private val petRepository: PetRepository
) {
    fun createPets() {
        for (i in 0..10) {
            val petModel = PetModel()
            petModel.name = "Jacky $i"
            petRepository.save(petModel)
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun listPets(): List<PetDto> {
        val result = ArrayList<PetDto>()
        petRepository.findAll().forEach { petModel ->
            run {
                result.add(
                    PetDto(
                        name = petModel.name!!,
                        id = petModel.id!!
                    )
                )
            }
        }

        return result
    }

    fun getPetById(petId: String): PetDto {
        val petModelOptional = petRepository.findById(petId.toLong())
        if (petModelOptional.isPresent) {
            return PetDto(
                name = petModelOptional.get().name!!,
                id = petModelOptional.get().id!!
            )
        }
        throw RuntimeException("Not found")
    }
}
