package de.imedia24.shop.service

import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.db.repository.ProductRepository
import de.imedia24.shop.domain.product.PartialProductUpdateRequest
import de.imedia24.shop.domain.product.SaveProductRequest
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.domain.product.ProductResponse.Companion.toProductResponse
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun findProductBySku(sku: String): ProductResponse? {
        val product = productRepository.findBySku(sku)
        return product?.toProductResponse()
    }

    fun addProduct(product: SaveProductRequest): ProductResponse? {
        val productToSave = ProductEntity(
            sku = product.sku,
            name = product.name,
            description = product.description,
            price = product.price,
            createdAt = ZonedDateTime.now(),
            updatedAt = ZonedDateTime.now()
        )
        return productRepository.save(productToSave).toProductResponse()
    }

    fun updateProduct(productToUpdate: PartialProductUpdateRequest): ProductResponse? {
        // Retrieve the existing product based on the SKU
        val existingProduct = productRepository.findBySku(productToUpdate.sku) ?: return null

        // If the product doesn't exist, return null
        val updatedProduct = ProductEntity(
            sku = existingProduct.sku,
            name = productToUpdate.name,
            description = productToUpdate.description,
            price = productToUpdate.price,
            createdAt = existingProduct.createdAt,
            updatedAt = ZonedDateTime.now()
        )

        productRepository.save(updatedProduct)

        // Create and return a ProductResponse object representing the updated product
        return ProductResponse(
            sku = updatedProduct.sku,
            name = updatedProduct.name,
            description = updatedProduct.description?: "",
            price = updatedProduct.price
        )
    }
}
