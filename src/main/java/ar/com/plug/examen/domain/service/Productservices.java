package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.DTOs.ProductDTO;
import ar.com.plug.examen.domain.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Productservices {

    ResponseEntity<Object> getProduct();

    ResponseEntity<Object> save(ProductDTO request);

    ResponseEntity<Object> delete(int id);

    ResponseEntity<Object> getProductById(int id);

    ResponseEntity<Object> modify(ProductDTO request);
}
