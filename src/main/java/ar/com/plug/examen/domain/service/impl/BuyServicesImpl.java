package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.DTOs.BuyDTO;
import ar.com.plug.examen.domain.DTOs.ResponseDTO;
import ar.com.plug.examen.domain.mappers.BuyMapper;
import ar.com.plug.examen.domain.mappers.ResponseMapper;
import ar.com.plug.examen.domain.model.Buy;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.BuyRepository;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.BuyServices;
import ar.com.plug.examen.domain.service.ClientServices;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BuyServicesImpl implements BuyServices {

    @Autowired
    BuyRepository buyRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ResponseMapper responseMapper;

    @Autowired
    BuyMapper buyMapper;

    @Override
    public ResponseEntity getBuyById(int id) {
        Buy bResp = buyRepository.findByIdEquals(id);
        if(bResp != null) {
            BuyDTO dto = buyMapper.generateBuyDTO(bResp);
            ResponseDTO resp = responseMapper.generateResponse(dto, "Proceso Completado con éxito");
            return new ResponseEntity(resp, HttpStatus.OK);
        }
        else{
            ResponseDTO resp = responseMapper.generateFallResponse("Mal pasado el parametro");
            return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity postBuy(BuyDTO buyDTO) {
        Buy newBuy = buyMapper.generateBuy(buyDTO);
        Client client = clientRepository.findByIdEquals(buyDTO.getId_client());
        Seller seller = sellerRepository.findByIdEquals(buyDTO.getId_seller());
        newBuy.setClient(client);
        newBuy.setSeller(seller);
        Buy bResp = buyRepository.save(newBuy);
        if(bResp != null) {
            BuyDTO dto = buyMapper.generateBuyDTO(bResp);
            ResponseDTO resp = responseMapper.generateResponse(dto, "Proceso Completado con éxito");
            return new ResponseEntity(resp, HttpStatus.CREATED);
        }
        else{
            ResponseDTO resp = responseMapper.generateFallResponse("Mal pasado el parametro");
            return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
        }
    }
}
