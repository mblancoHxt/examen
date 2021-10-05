package ar.com.plug.examen.domain.controller;

import ar.com.plug.examen.domain.DTOs.ProductDTO;
import ar.com.plug.examen.domain.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductServices productService;

    //@RequestMapping(value = "/getProduct", method = RequestMethod.GET)
    @GetMapping("/")
    public ResponseEntity<Object> getProducts(){
        return productService.getProduct();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductsbyId(@PathVariable int id){
        return productService.getProductById(id);
    }

    //@RequestMapping(value = "/postProduct", method = RequestMethod.POST)
    @PostMapping("/")
    public ResponseEntity<Object> saveProduct(@RequestBody ProductDTO request){
        return productService.save(request);
    }

    //@RequestMapping(value = "deleteProduct", method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable int id){
        return productService.delete(id);
    }

    @PutMapping("/")
    public ResponseEntity<Object> modifyProduct(@RequestBody ProductDTO request){
        return productService.modify(request);
    }



}
