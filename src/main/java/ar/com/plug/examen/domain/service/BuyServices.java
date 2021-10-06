package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.DTOs.BuyDTO;
import org.springframework.http.ResponseEntity;

public interface BuyServices {
    ResponseEntity getBuyById(int id);

    ResponseEntity postBuy(BuyDTO buyDTO);
}
