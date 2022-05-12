package io.github.ragin_lundf.domain_models.pets

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "PET")
class PetModel: Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_sequence_generator")
    @SequenceGenerator(name = "pet_sequence_generator", sequenceName = "SEQ_PET", allocationSize = 1)
    @Column(name = "ID", updatable = false, nullable = false)
    var id: Long? = null

    @Column(name = "NAME")
    var name: String? = null

    companion object {
        private const val serialVersionUID = -4049571791997019824L
    }
}
