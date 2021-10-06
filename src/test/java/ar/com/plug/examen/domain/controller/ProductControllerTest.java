package ar.com.plug.examen.domain.controller;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.domain.DTOs.ProductDTO;
import ar.com.plug.examen.domain.DTOs.ResponseDTO;
import ar.com.plug.examen.domain.service.ProductServices;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
class ProductControllerTest {

    @MockBean
    private ProductServices productServices ;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String URL = "/product/";

    @Test
    void getProducts() {
        ResponseEntity<ResponseDTO> response = restTemplate.getForEntity(URL,ResponseDTO.class);
        when(this.productServices.getProduct()).thenReturn(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getProductsbyId() {
        ResponseEntity<Object> response = restTemplate.getForEntity(URL + 1, Object.class);
        when(this.productServices.getProductById(1)).thenReturn(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void saveProduct() {
        Float price = new Float(150.38);
        ProductDTO prod = new ProductDTO("Camiseta", price);
        ResponseEntity<Object> response = restTemplate.postForEntity(URL,prod,Object.class);
        when(this.productServices.save(prod)).thenReturn(response);
        assertEquals(HttpStatus.OK , response.getStatusCode());
        verify(this.productServices,times(1)).save(refEq(prod));
    }

    @Test
    void deleteProduct() {
        restTemplate.delete(URL  + 1);
        verify(this.productServices, times(1)).delete(1);
    }

    @Test
    void modifyProduct() {
        Float price = new Float(23.30);
        ProductDTO prod = new ProductDTO(1, "camiseta", price);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ProductDTO> request = new HttpEntity<ProductDTO>(prod,headers);
        ResponseEntity<ResponseDTO> response = restTemplate.exchange(URL, HttpMethod.PUT,request,ResponseDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        when(this.productServices.modify(prod)).thenReturn(response);
        verify(this.productServices, times(1)).modify(refEq(prod));
    }
}