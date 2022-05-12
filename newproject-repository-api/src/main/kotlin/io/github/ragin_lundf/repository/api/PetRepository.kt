package io.github.ragin_lundf.repository.api

import io.github.ragin_lundf.domain_models.pets.PetModel
import java.util.Optional

interface PetRepository {
    fun save(pet: PetModel): PetModel

    fun findAll(): List<PetModel>

    fun findById(id: Long): Optional<PetModel>
}
