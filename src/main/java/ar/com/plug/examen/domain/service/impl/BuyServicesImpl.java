package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.DTOs.BuyDTO;
import ar.com.plug.examen.domain.DTOs.BuyDetailsDTO;
import ar.com.plug.examen.domain.DTOs.ResponseDTO;
import ar.com.plug.examen.domain.mappers.BuyMapper;
import ar.com.plug.examen.domain.mappers.ResponseMapper;
import ar.com.plug.examen.domain.model.*;
import ar.com.plug.examen.domain.repository.*;
import ar.com.plug.examen.domain.service.BuyServices;
import ar.com.plug.examen.domain.service.ClientServices;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service

public class BuyServicesImpl implements BuyServices {

    @Autowired
    BuyRepository buyRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BuyDetailsRepository buyDetailsRepository;

    @Autowired
    ResponseMapper responseMapper;

    @Autowired
    BuyMapper buyMapper;

    @Override
    public ResponseEntity getBuyById(int id) {
        Buy bResp = buyRepository.findByIdEquals(id);
        if (bResp != null) {
            if(bResp.getClient().getStatus() > 0) {
                BuyDTO dto = buyMapper.generateBuyDTO(bResp);
                ResponseDTO resp = responseMapper.generateResponse(dto, "Proceso Completado con éxito");
                return new ResponseEntity(resp, HttpStatus.OK);
            } else {
                ResponseDTO resp = responseMapper.generateFallResponse("No existe la compra o está eliminada");
                return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
            }
        } else {
            ResponseDTO resp = responseMapper.generateFallResponse("No existe la compra o está eliminada");
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
        if(client.getStatus()>0) {
            if(seller.getStatus()>0) {
                Buy bResp = buyRepository.save(newBuy);
                if(bResp != null) {
                    BuyDTO dto = buyMapper.generateBuyDTO(bResp);
                    ResponseDTO resp = responseMapper.generateResponse(dto, "Proceso Completado con éxito");
                    return new ResponseEntity(resp, HttpStatus.CREATED);
                }
                else{
                    ResponseDTO resp = responseMapper.generateFallResponse("Mal pasado algún parametro");
                    return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
                }
            }
            else{
                ResponseDTO resp = responseMapper.generateFallResponse("El vendedor está suspendido o eliminado");
                return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
            }
        }
        else{
            ResponseDTO resp = responseMapper.generateFallResponse("El cliente está suspendido o eliminado");
            return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity postDetails(BuyDetailsDTO buyDetailsDTO) {
        Product product = productRepository.findById(buyDetailsDTO.getId_product());
        if(product != null){
            Buy buy = buyRepository.findByIdEquals(buyDetailsDTO.getId_buy());
            if(buy != null) {
                if(buy.getSeller().getStatus() > 0) {
                    if(buy.getClient().getStatus() > 0) {
                        BuyDetails buyDetails = new BuyDetails();
                        buyDetails.setBuy(buy);
                        buyDetails.setProduct(product);
                        buyDetails.setCant(buyDetailsDTO.getCant());

                        BuyDetails buyNew = buyDetailsRepository.save(buyDetails);
                        if (buyNew != null) {
                            BuyDetailsDTO data = buyMapper.generateBuyDetailDTO(buyNew);
                            ResponseDTO resp = responseMapper.generateResponse(data, "Guardado con éxito");
                            return new ResponseEntity(resp, HttpStatus.OK);
                        } else {
                            ResponseDTO resp = responseMapper.generateFallResponse("No se pudo guardar");
                            return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
                        }
                    }
                    else {
                        ResponseDTO resp = responseMapper.generateFallResponse("El cliente está eliminado o suspendido");
                        return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
                    }
                }
                else {
                    ResponseDTO resp = responseMapper.generateFallResponse("El vendedor está eliminado o suspendido");
                    return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
                }

            }
            else {
                ResponseDTO resp = responseMapper.generateFallResponse("No se encuentra la compra ingresada");
                return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
            }
        }
        else{
            ResponseDTO resp = responseMapper.generateFallResponse("No se encuentra el producto ingresado");
            return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity getBuysByProductId(int id) {
        List<Buy> listBuy = buyRepository.findByListDetails_Product_IdEqualsAndClient_StatusGreaterThanAndSeller_StatusGreaterThan(id,0,0);
        if(listBuy != null){
            List<BuyDTO> listDTO = new ArrayList<>();
            for(Buy buy : listBuy){
                BuyDTO dto = buyMapper.generateBuyDTO(buy);
                listDTO.add(dto);
            }
            ResponseDTO resp = responseMapper.generateResponse(listDTO, "Correcto");
            return new ResponseEntity(resp, HttpStatus.OK);
        }
        else{
            ResponseDTO resp = responseMapper.generateFallResponse("No se encuentra el producto ingresado");
            return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity getBuysByClient(int id) {
        List<Buy> listBuy = buyRepository.findByClient_IdEqualsAndClient_StatusGreaterThanAndClient_StatusGreaterThan(id,0,0);
        if(listBuy != null){
            List<BuyDTO> listDTO = new ArrayList<>();
            for(Buy buy : listBuy){
                BuyDTO dto = buyMapper.generateBuyDTO(buy);
                listDTO.add(dto);
            }
            ResponseDTO resp = responseMapper.generateResponse(listDTO, "Correcto");
            return new ResponseEntity(resp, HttpStatus.OK);
        }
        else{
            ResponseDTO resp = responseMapper.generateFallResponse("No se encuentra el producto ingresado");
            return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
        }
    }


}
