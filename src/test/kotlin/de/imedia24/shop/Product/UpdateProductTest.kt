package de.imedia24.shop.Product

import de.imedia24.shop.domain.product.PartialProductUpdateRequest
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.domain.product.SaveProductRequest
import de.imedia24.shop.service.ProductService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest



@SpringBootTest
@AutoConfigureMockMvc
class UpdateProductTest {

    @Mock
    private lateinit var productService: ProductService


    @Test
    fun testUpdateProduct() {
        // Arrange
        val sku = "123"
        val updatedProductRequest = PartialProductUpdateRequest(
            sku = sku,
            name = "Product 1 UPDATED",
            description = "Product 1 UPDATED",
            price = 9999.0.toBigDecimal()
        )

        val productResponse = ProductResponse(
            sku = sku,
            name = "Product 1 UPDATED",
            description = "Product 1 UPDATED",
            price = 9999.0.toBigDecimal()
        )

        `when`(productService.updateProduct(updatedProductRequest)).thenReturn(productResponse)

        // Act
        val result = productService.updateProduct(updatedProductRequest)

        // Assert
        assertNotNull(result)
        assertEquals(productResponse, result)

        // Verify
        verify(productService).updateProduct(updatedProductRequest)
    }

}
