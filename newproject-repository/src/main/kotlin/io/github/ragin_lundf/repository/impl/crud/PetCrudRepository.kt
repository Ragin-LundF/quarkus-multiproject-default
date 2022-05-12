package io.github.ragin_lundf.repository.impl.crud

import io.github.ragin_lundf.domain_models.pets.PetModel
import org.springframework.data.repository.CrudRepository

interface PetCrudRepository: CrudRepository<PetModel, Long> {
}
