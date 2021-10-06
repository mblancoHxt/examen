package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.DTOs.ProductDTO;
import ar.com.plug.examen.domain.DTOs.ResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductServices {

    ResponseEntity getProduct();

    ResponseEntity save(ProductDTO request);

    ResponseEntity delete(int id);

    ResponseEntity getProductById(int id);

    ResponseEntity modify(ProductDTO request);
}
