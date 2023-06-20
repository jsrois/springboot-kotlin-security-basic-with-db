package net.jsrois.api.repositories

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "products")
class Product(
    var name: String,
    @Id
    var id: UUID
)
