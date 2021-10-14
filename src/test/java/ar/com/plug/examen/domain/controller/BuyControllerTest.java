package ar.com.plug.examen.domain.controller;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.domain.DTOs.BuyDTO;
import ar.com.plug.examen.domain.DTOs.BuyDetailsDTO;
import ar.com.plug.examen.domain.DTOs.ClientDTO;
import ar.com.plug.examen.domain.DTOs.ResponseDTO;
import ar.com.plug.examen.domain.model.BuyDetails;
import ar.com.plug.examen.domain.service.BuyServices;
import ar.com.plug.examen.domain.service.ClientServices;
import org.hibernate.cfg.CreateKeySecondPass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
class BuyControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private BuyServices buyServices;

    private final String URL = "/buy/";

    @Test
    void getBuyById() {
        ResponseEntity<ResponseDTO> response= restTemplate.getForEntity(URL+ 2, ResponseDTO.class);
        assertTrue(response.getStatusCode() == HttpStatus.OK);
        when(this.buyServices.getBuyById(2)).thenReturn(response);
        verify(this.buyServices, times(1)).getBuyById(2);

    }

    @Test
    void postBuy() {
        BuyDTO buy = new BuyDTO();
        buy.setId_client(9);
        buy.setId_seller(1);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<BuyDTO> request = new HttpEntity<>(buy, headers);
        ResponseEntity<ResponseDTO> response = restTemplate.postForEntity(URL, request, ResponseDTO.class);
        assertTrue(response.getStatusCode() == HttpStatus.OK);
    }

    @Test
    void postDetail() {
        BuyDetailsDTO detailsDTO = new BuyDetailsDTO();
        detailsDTO.setId_buy(13);
        detailsDTO.setId_product(12);
        detailsDTO.setCant(2);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<BuyDetailsDTO> request = new HttpEntity<>(detailsDTO, headers);
        ResponseEntity<ResponseDTO> response = restTemplate.postForEntity(URL, request, ResponseDTO.class);
        assertTrue(response.getStatusCode() == HttpStatus.OK);
    }
}