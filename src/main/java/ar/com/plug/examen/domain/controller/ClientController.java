package ar.com.plug.examen.domain.controller;

import ar.com.plug.examen.domain.DTOs.ClientDTO;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.service.ClientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientServices clientServices;

    @GetMapping("/")
    public ResponseEntity<Object> getAllClient(){
        return clientServices.getAllClient();

    }

    @PostMapping("/")
    public ResponseEntity postClient(@RequestBody ClientDTO request){
        return clientServices.saveClient(request);
    }

    @PutMapping("/")
    public ResponseEntity putClient(@RequestBody ClientDTO request){
        return clientServices.modifyClient(request);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity deleteClient(@PathVariable Integer id) { return  clientServices.deleteClient(id);}

    @GetMapping("/{id}")
    public  ResponseEntity getClient(@PathVariable Integer id){ return clientServices.getClient(id);}

}
