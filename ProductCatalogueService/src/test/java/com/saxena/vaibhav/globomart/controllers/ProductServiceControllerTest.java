package com.saxena.vaibhav.globomart.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saxena.vaibhav.globomart.controllers.ProductServiceController;
import com.saxena.vaibhav.globomart.dto.ProductDto;
import com.saxena.vaibhav.globomart.service.ProductService;

public class ProductServiceControllerTest {

	@Mock
    ProductService productService;

    @InjectMocks
    ProductServiceController productServiceController;

    MockMvc mockMvc;
    
    private final String TYPE = "TYPE1";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(productServiceController)
                //.setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }
    
    @Test
    public void testGetProductsByType() throws Exception {

        ProductDto product1 = new ProductDto();
        product1.setDescription("My Test Prouduct 1");
        product1.setName("My_TEST_PRODUCT_1");
        product1.setProductType("TYPE1");

        ProductDto product2 = new ProductDto();
        product1.setDescription("My Test Prouduct 2");
        product1.setName("My_TEST_PRODUCT_2");
        product1.setProductType("TYPE1");
        when(productService.findByProductType(TYPE)).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(get("/v1/catalog/cat1/product/producttype/TYPE1") //Vaibhav: TODO: Fix Hardcoded URL
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testAddProduct() throws Exception {
        ProductDto product = new ProductDto();
        product.setDescription("My Test Prouduct 1");
        product.setName("My_TEST_PRODUCT_1");
        product.setProductType("TYPE1");
        
        ProductDto returnDTO = new ProductDto();
        returnDTO.setDescription(product.getDescription());
        returnDTO.setName(product.getName());
        returnDTO.setProductType(product.getProductType());

        when(productService.addProduct(product)).thenReturn(returnDTO);

        mockMvc.perform(post("/v1/catalog/cat1/product") //Vaibhav: TODO: Fix Hardcoded URL
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(product)))
                .andExpect(status().isCreated());
    }
    
//    @Test //Vaibhav: TODO: Fix this
    public void testDeleteProduct() throws Exception {

        mockMvc.perform(delete("/v1/catalog/cat1/product/1") //Vaibhav: TODO: Fix Hardcoded URL
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService).deleteProduct("1");
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
