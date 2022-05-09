package io.github.ragin_lundf.newproject.impl.pets

import io.github.ragin_lundf.domain_services.pets.PetDomainService
import io.github.ragin_lundf.newproject.dto.pets.models.ApiResponseDto
import io.github.ragin_lundf.newproject.dto.pets.models.PetDto
import io.github.ragin_lundf.newproject.pets.PetApi
import java.io.InputStream
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PetApiController(private val petDomainService: PetDomainService): PetApi {
    override fun addPet(body: PetDto) {
        TODO("Not yet implemented")
    }

    override fun deletePet(petId: Long, apiKey: String?) {
        TODO("Not yet implemented")
    }

    override fun findPetsByStatus(status: List<String>): List<PetDto> {
        TODO("Not yet implemented")
    }

    override fun findPetsByTags(tags: List<String>): List<PetDto> {
        TODO("Not yet implemented")
    }

    override fun getPetById(petId: Long): PetDto {
        return petDomainService.getPetById(1)
    }

    override fun updatePet(body: PetDto) {
        TODO("Not yet implemented")
    }

    override fun updatePetWithForm(petId: Long, name: String?, status: String?) {
        TODO("Not yet implemented")
    }

    override fun uploadFile(petId: Long, additionalMetadata: String?, fileInputStream: InputStream?): ApiResponseDto {
        TODO("Not yet implemented")
    }
}
