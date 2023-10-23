package de.imedia24.shop.controller

import de.imedia24.shop.domain.product.PartialProductUpdateRequest
import de.imedia24.shop.domain.product.SaveProductRequest
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.service.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@Suppress("NAME_SHADOWING")
@RestController
@Tag(name = "Product API")
class ProductController(private val productService: ProductService) {

    private val logger = LoggerFactory.getLogger(ProductController::class.java)!!


    @GetMapping("/products/{sku}", produces = ["application/json;charset=utf-8"])
    @Operation(
        summary = "Find product by SKU",
        description = "Retrieves a product based on its SKU.",
        responses = [ApiResponse(responseCode = "200", description = "Product found"), ApiResponse(
            responseCode = "404", description = "Product not found", content = [Content()]
        )]
    )

    fun findProductsBySku(
        @PathVariable("sku") sku: String
    ): ResponseEntity<ProductResponse> {
        logger.info("Request for product $sku")

        val product = productService.findProductBySku(sku)
        return if (product == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(product)
        }
    }


    @GetMapping("/products", produces = ["application/json;charset=utf-8"])
    @Operation(
        summary = "Find products by SKU query",
        description = "Retrieves product based on a SKU query parameter.",
        responses = [ApiResponse(
            responseCode = "200", description = "Products found"
        ), ApiResponse(responseCode = "404", description = "Product not found", content = [Content()])]
    )


    fun findProsductsBySkuQuery(
        @RequestParam("sku") sku: String
    ): ResponseEntity<ProductResponse> {
        logger.info("Request for product $sku")

        val product = productService.findProductBySku(sku)
        return if (product == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(product)
        }
    }


    @PostMapping("/products", consumes = ["application/json"], produces = ["application/json;charset=utf-8"])
    @Operation(
        summary = "Save a new product", description = "Creates a new product.", responses = [ApiResponse(
            responseCode = "201", description = "Product created"
        ), ApiResponse(responseCode = "400", description = "Invalid request", content = [Content()])]
    )

    fun saveProduct(@RequestBody product: SaveProductRequest): ResponseEntity<ProductResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product))
    }


    @PatchMapping("/products", consumes = ["application/json"], produces = ["application/json;charset=utf-8"])
    @Operation(
        summary = "Update a product", description = "Updates an existing product.", responses = [ApiResponse(
            responseCode = "200", description = "Product updated"
        ), ApiResponse(responseCode = "404", description = "Product not found", content = [Content()])]
    )
    fun updateProduct(
        @RequestBody updatedProduct: PartialProductUpdateRequest
    ): ResponseEntity<ProductResponse> {
        val responseProduct = productService.updateProduct(updatedProduct)
        return if (responseProduct == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(responseProduct)
        }
    }


}
