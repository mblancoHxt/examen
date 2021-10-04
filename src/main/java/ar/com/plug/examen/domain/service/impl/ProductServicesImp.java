package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.DTOs.ProductDTO;
import ar.com.plug.examen.domain.DTOs.ResponseDTO;
import ar.com.plug.examen.domain.mappers.ProductMapper;
import ar.com.plug.examen.domain.mappers.ResponseMapper;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.Productservices;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServicesImp implements Productservices {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ResponseMapper responseMapper;

    @Autowired
    private ProductMapper productMapper;


    @Override
    public ResponseEntity<Object> getProduct( ) {
        List<Product> listProd =  productRepository.findAll();
        return new ResponseEntity<Object>(responseMapper.generateResponse(listProd, "Todo bien"), HttpStatus.OK) ;
    }

    @Override
    public ResponseEntity<Object> save(ProductDTO request) {
        Product prod = productMapper.generateProduct(request);
        Product prodNew = productRepository.save(prod);
        if(prodNew != null){
            ResponseDTO dto = responseMapper.generateResponse( prodNew,"Se creo con exito");
            return new ResponseEntity<Object>(dto, HttpStatus.CREATED);
        }
        else{
            ResponseDTO dto = responseMapper.generateFallResponse("No se puede guardar, ya existe");
            return new ResponseEntity<Object>(dto, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> delete(int id) {
        Product prod = productRepository.findById(id);
        if(prod != null) {
            productRepository.delete(prod);
            ProductDTO resp = productMapper.generateDTO(prod);
            return new ResponseEntity<Object>(resp, HttpStatus.OK);
        }
        else{
            ResponseDTO dto = responseMapper.generateFallResponse("No se puede borrar, no existe");
            return new ResponseEntity<Object>(dto, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> getProductById(int id) {
        Product prod = productRepository.findById(id);

        if(prod != null){
            ProductDTO resp = productMapper.generateDTO(prod);
            return new ResponseEntity<Object>(resp, HttpStatus.BAD_REQUEST);
        }
        else{
            ResponseDTO dto = responseMapper.generateFallResponse("No se puede obtener, no existe");
            return new ResponseEntity<Object>(dto, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> modify(ProductDTO request) {
        Product prod = productMapper.generateProduct(request);
        if(productRepository.findById(prod.getId()) != null) {
            Product prodNew = productRepository.save(prod);
            ProductDTO dtoProd = productMapper.generateDTO(prodNew);
            ResponseDTO dto = responseMapper.generateResponse(dtoProd, "Se modificó correctamente");

            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        else{
            ResponseDTO dto = responseMapper.generateFallResponse("No existe el Producto buscado");
            return new ResponseEntity<Object>(dto, HttpStatus.BAD_REQUEST);
        }
    }
}
