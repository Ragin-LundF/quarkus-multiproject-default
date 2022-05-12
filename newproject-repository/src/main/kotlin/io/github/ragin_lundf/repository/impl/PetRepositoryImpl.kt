package io.github.ragin_lundf.repository.impl

import io.github.ragin_lundf.domain_models.pets.PetModel
import io.github.ragin_lundf.repository.api.PetRepository
import io.github.ragin_lundf.repository.impl.crud.PetCrudRepository
import java.util.Optional

class PetRepositoryImpl(
    private val petCrudRepository: PetCrudRepository
): PetRepository {
    override fun save(pet: PetModel): PetModel {
        return petCrudRepository.save(pet)
    }

    override fun findAll(): List<PetModel> {
        return petCrudRepository.findAll().toList()
    }

    override fun findById(id: Long): Optional<PetModel> {
        return petCrudRepository.findById(id)
    }
}
