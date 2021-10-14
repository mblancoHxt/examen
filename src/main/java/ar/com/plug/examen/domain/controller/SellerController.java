package ar.com.plug.examen.domain.controller;

import ar.com.plug.examen.domain.DTOs.SellerDTO;
import ar.com.plug.examen.domain.service.SellerServices;
import org.hibernate.cache.spi.SecondLevelCacheLogger_$logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerServices sellerServices;

    @GetMapping("/{id}")
    public ResponseEntity getSellerById(@PathVariable("id") Integer id){
        return sellerServices.getSellerById(id);
    }

    @PostMapping("/")
    public ResponseEntity postSeller(@RequestBody SellerDTO seller){
        return sellerServices.postSeller(seller);
    }

    @PutMapping("/")
    public ResponseEntity putSeller(@RequestBody SellerDTO sellerDTO){
        return sellerServices.putSeller(sellerDTO);
    }

}
