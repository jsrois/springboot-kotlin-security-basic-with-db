package net.jsrois.api.controllers

import net.jsrois.api.repositories.Product

data class ProductDTO(
    val name: String,
    val id: String
) {
    companion object {
        fun from(product: Product) =
            with(product) {
                ProductDTO(name, id.toString())
            }
    }
}
