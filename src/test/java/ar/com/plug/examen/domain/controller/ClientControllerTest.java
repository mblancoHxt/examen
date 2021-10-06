package ar.com.plug.examen.domain.controller;

import ar.com.plug.examen.Application;
import ar.com.plug.examen.domain.DTOs.ClientDTO;
import ar.com.plug.examen.domain.DTOs.ResponseDTO;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.ClientServices;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
class ClientControllerTest {

    @MockBean
    private ClientServices clientService ;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String URL = "/client/";

    @Test
    void getAllClient() {
        ResponseEntity<ResponseDTO> response= restTemplate.getForEntity(URL, ResponseDTO.class);
        assertTrue(response.getStatusCode() == HttpStatus.OK);
        when(this.clientService.getAllClient()).thenReturn(response);
        verify(this.clientService, times(1)).getAllClient();
    }

    @Test
    void postClient() {
        ClientDTO cl = new ClientDTO("user",38897074,"Pedro", "Rodriguez","Calle 1234","342587875", "mail@mail.com","M", "pass");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ClientDTO> request = new HttpEntity<>(cl, headers);
        ResponseEntity<ResponseDTO> response = restTemplate.postForEntity(URL, request, ResponseDTO.class);
        assertTrue(response.getStatusCode() == HttpStatus.OK);
    }

    @Test
    void putClient() {
        ClientDTO cl = new ClientDTO(1, 38897074, "Carlos", "Blanco", "Chaco 5416", "+ 54 342 4 293613", "cblanco@mail.com", "M", "mblanco", "mblanco");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ClientDTO> request = new HttpEntity<>(cl, headers);
        ResponseEntity<ResponseDTO> response = restTemplate.exchange(URL, HttpMethod.PUT,request,ResponseDTO.class);
        assertTrue(response.getStatusCode() == HttpStatus.OK);
        when(this.clientService.modifyClient(cl)).thenReturn(response);
        verify(this.clientService, times(1)).modifyClient(refEq(cl));
    }

    @Test
    void deleteClient() {
        restTemplate.delete(URL + 1);
        verify(this.clientService, times(1)).deleteClient(1);
    }

    @Test
    void getClient() {
        ClientDTO cl = new ClientDTO(1, 38897074, "Carlos", "Blanco", "Chaco 5416", "+ 54 342 4 293613", "cblanco@mail.com", "M", "mblanco", "mblanco");
        ResponseEntity<ResponseDTO> response = restTemplate.getForEntity(URL + 1, ResponseDTO.class);
        when(this.clientService.getClient(1)).thenReturn(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
}