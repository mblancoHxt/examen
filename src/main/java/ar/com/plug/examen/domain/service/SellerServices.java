package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.DTOs.SellerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface SellerServices {
    ResponseEntity getSellerById(Integer id);

    ResponseEntity postSeller(SellerDTO seller);

    ResponseEntity putSeller(SellerDTO sellerDTO);

    ResponseEntity deleteSeller(Integer id);
}
