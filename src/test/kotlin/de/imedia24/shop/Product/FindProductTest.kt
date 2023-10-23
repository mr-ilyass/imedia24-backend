package de.imedia24.shop.Product

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@SpringBootTest
@AutoConfigureMockMvc
class FindProductTest {

    @Autowired
    private val mockMvc: MockMvc? = null



    @Test
    fun testFindProductsBySkuQuery_ProductFound() {
        val sku = "123"

        mockMvc?.perform(
            MockMvcRequestBuilders.get("/products")
                .param("sku", sku)
                .accept(MediaType.APPLICATION_JSON)
        )
            ?.andExpect(MockMvcResultMatchers.status().isOk)
            ?.andExpect(MockMvcResultMatchers.jsonPath("$.sku").value(sku))
            ?.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Product 1"))
            ?.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(10.0))
    }

    @Test
    fun testFindProductsBySkuQuery_ProductNotFound() {
        val sku = "DEF456"
        mockMvc?.perform(
            MockMvcRequestBuilders.get("/products")
                .param("sku", sku)
                .accept(MediaType.APPLICATION_JSON)
        )
            ?.andExpect(MockMvcResultMatchers.status().isNotFound)
    }



}