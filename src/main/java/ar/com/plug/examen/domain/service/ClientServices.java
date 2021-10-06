package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.DTOs.ClientDTO;
import org.springframework.http.ResponseEntity;

public interface ClientServices {
    ResponseEntity getAllClient();

    ResponseEntity saveClient(ClientDTO request);

    ResponseEntity modifyClient(ClientDTO request);

    ResponseEntity deleteClient(Integer id);

    ResponseEntity getClient(Integer id);
}
