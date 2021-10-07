package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.DTOs.ProductDTO;
import ar.com.plug.examen.domain.DTOs.ResponseDTO;
import ar.com.plug.examen.domain.mappers.ProductMapper;
import ar.com.plug.examen.domain.mappers.ResponseMapper;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServicesImp implements ProductServices {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ResponseMapper responseMapper;

    @Autowired
    private ProductMapper productMapper;


    @Override
    public ResponseEntity<Object> getProduct( ) {
        List<Product> listProd =  productRepository.findAll();
        List<ProductDTO> listResp = productMapper.generateListDTO(listProd);
        ResponseDTO resp = responseMapper.generateResponse(listResp, "Proceso completado");
        return new ResponseEntity<Object>(resp, HttpStatus.OK) ;
    }

    @Override
    public ResponseEntity save(ProductDTO request) {
        Product prod = productMapper.generateProduct(request);
        Product prodNew = productRepository.save(prod);
        ProductDTO prodData = productMapper.generateDTO(prodNew);
        if(prodNew != null){
            ResponseDTO dto = responseMapper.generateResponse( prodData,"Se creo con exito");
            return new ResponseEntity(dto, HttpStatus.CREATED);
        }
        else{
            ResponseDTO dto = responseMapper.generateFallResponse("No se puede guardar, ya existe");
            return new ResponseEntity(dto, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity delete(int id) {
        Product prod = productRepository.findById(id);
        if(prod != null) {
            productRepository.delete(prod);
            ProductDTO dto = productMapper.generateDTO(prod);
            ResponseDTO resp = responseMapper.generateResponse(dto, "Proceso correcto");
            return new ResponseEntity(resp, HttpStatus.OK);
        }
        else{
            ResponseDTO dto = responseMapper.generateFallResponse("No se puede borrar, no existe");
            return new ResponseEntity(dto, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity getProductById(int id) {
        Product prod = productRepository.findById(id);

        if(prod != null){
            ProductDTO dto = productMapper.generateDTO(prod);
            ResponseDTO resp = responseMapper.generateResponse(dto, "Proceso correcto");
            return new ResponseEntity(resp, HttpStatus.OK);
        }
        else{
            ResponseDTO dto = responseMapper.generateFallResponse("No se puede obtener, no existe");
            return new ResponseEntity(dto, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity modify(ProductDTO request) {
        Product prod = productMapper.generateProduct(request);
        if(productRepository.findById(prod.getId()) != null) {
            Product prodNew = productRepository.save(prod);
            ProductDTO dtoProd = productMapper.generateDTO(prodNew);
            ResponseDTO dto = responseMapper.generateResponse(dtoProd, "Se modificó correctamente");

            return new ResponseEntity(dto, HttpStatus.OK);
        }
        else{
            ResponseDTO dto = responseMapper.generateFallResponse("No existe el Producto buscado");
            return new ResponseEntity(dto, HttpStatus.BAD_REQUEST);
        }
    }
}
