package ar.com.plug.examen.domain.controller;

import ar.com.plug.examen.domain.DTOs.BuyDTO;
import ar.com.plug.examen.domain.DTOs.BuyDetailsDTO;
import ar.com.plug.examen.domain.service.BuyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/buy")
public class BuyController {

    @Autowired
    BuyServices buyServices;

    @GetMapping("/{id}")
    ResponseEntity getBuyById(@PathVariable int id){
        return buyServices.getBuyById(id);
    }

    @PostMapping("/")
    ResponseEntity postBuy(@RequestBody BuyDTO buyDTO){
        return buyServices.postBuy(buyDTO);
    }

    @PostMapping("/details")
    ResponseEntity postDetail(@RequestBody BuyDetailsDTO buyDetailsDTO){
        return buyServices.postDetails(buyDetailsDTO);
    }

    @GetMapping("/products/{id}")
    ResponseEntity getPorProduct(@PathVariable int id){
        return buyServices.getBuysByProductId(id);
    }

    @GetMapping("/clients/{id}")
    ResponseEntity getPorClients(@PathVariable int id){
        return buyServices.getBuysByClient(id);
    }


}
