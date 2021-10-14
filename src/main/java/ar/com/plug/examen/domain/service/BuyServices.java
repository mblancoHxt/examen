package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.DTOs.BuyDTO;
import ar.com.plug.examen.domain.DTOs.BuyDetailsDTO;
import org.springframework.http.ResponseEntity;

public interface BuyServices {
    ResponseEntity getBuyById(int id);

    ResponseEntity postBuy(BuyDTO buyDTO);

    ResponseEntity postDetails(BuyDetailsDTO buyDetailsDTO);

    ResponseEntity getBuysByProductId(int id);

    ResponseEntity getBuysByClient(int id);
}
