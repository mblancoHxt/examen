package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.DTOs.ResponseDTO;
import ar.com.plug.examen.domain.DTOs.SellerDTO;
import ar.com.plug.examen.domain.mappers.ResponseMapper;
import ar.com.plug.examen.domain.mappers.SellerMapper;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.SellerServices;
import org.aspectj.weaver.patterns.HasMemberTypePatternForPerThisMatching;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.channels.ReadPendingException;

@Service
public class SellerServicesImp implements SellerServices {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    SellerMapper sellerMapper;

    @Autowired
    ResponseMapper responseMapper;

    @Override
    public ResponseEntity getSellerById(Integer id) {
        Seller seller = sellerRepository.findByIdEquals(id);
        if(seller != null) {
            if (seller.getStatus() > 0) {
                SellerDTO sellerDTO = sellerMapper.generateDTO(seller);
                ResponseDTO resp = responseMapper.generateResponse(sellerDTO, "Respuesta Correcta");
                return new ResponseEntity(resp, HttpStatus.OK);
            } else {
                ResponseDTO resp = responseMapper.generateFallResponse("No existe el Seller buscado");
                return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
            }
        }
        else{
            ResponseDTO resp = responseMapper.generateFallResponse("No existe el vendedor buscado o está suspendido");
            return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity postSeller(SellerDTO sellerDTO) {
        Seller seller = sellerMapper.generateSeller(sellerDTO);
        seller.setStatus(1);
        seller = sellerRepository.save(seller);
        SellerDTO dto = sellerMapper.generateDTO(seller);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity putSeller(SellerDTO sellerDTO) {
        Seller sellActual = sellerRepository.findByIdEquals(sellerDTO.getId());
        if(sellActual != null) {
            if(sellActual.getStatus()>0) {
                if (sellerDTO.getUsername() != null) {
                    sellActual.setUsername(sellerDTO.getUsername());
                }
                if (sellerDTO.getDni() != 0) {
                    sellActual.setDni(sellerDTO.getDni());
                }
                if (sellerDTO.getName() != null) {
                    sellActual.setName(sellerDTO.getName());
                }
                if (sellerDTO.getLastname() != null) {
                    sellActual.setLastname(sellerDTO.getLastname());
                }
                if (sellerDTO.getAddres() != null) {
                    sellActual.setAddres(sellerDTO.getAddres());
                }
                if (sellerDTO.getPhone() != null) {
                    sellActual.setPhone(sellerDTO.getPhone());
                }
                if (sellerDTO.getEmail() != null) {
                    sellActual.setEmail(sellerDTO.getEmail());
                }
                if (sellerDTO.getGender() != null) {
                    sellActual.setGender(sellerDTO.getGender());
                }
                if (sellerDTO.getPassword() != null) {
                    sellActual.setPassword(sellerDTO.getPassword());
                }
                sellActual = sellerRepository.save(sellActual);
                if (sellActual != null) {
                    SellerDTO dto = sellerMapper.generateDTO(sellActual);
                    ResponseDTO resp = responseMapper.generateResponse(dto, "Se guardó correctamente");
                    return new ResponseEntity(resp, HttpStatus.OK);
                } else {
                    ResponseDTO resp = responseMapper.generateFallResponse("No se encuentra el id seleccionado");
                    return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
                }
            }
            else{
                ResponseDTO resp = responseMapper.generateFallResponse("No se encuentra el id seleccionado");
                return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
            }
        }
        else{
            ResponseDTO resp = responseMapper.generateFallResponse("No se encuentra el id seleccionado");
            return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
        }


    }

    @Override
    public ResponseEntity deleteSeller(Integer id) {
        Seller sel = sellerRepository.findByIdEquals(id);
        if(sel != null){
            if(sel.getStatus() > 0 ){
                sel.setStatus(0);
                sel = sellerRepository.save(sel);
                if(sel != null){
                    SellerDTO data = sellerMapper.generateDTO(sel);
                    ResponseDTO dto = responseMapper.generateResponse(data, "Se borró con exito");
                    return new ResponseEntity(dto, HttpStatus.OK);
                }
                else{
                    ResponseDTO resp = responseMapper.generateFallResponse("No se pudo borrar el vendedor");
                    return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
                }
            }
            else{
                ResponseDTO resp = responseMapper.generateFallResponse("El vendedor se encuentra eliminado o suspendido");
                return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
            }
        }
        ResponseDTO resp = responseMapper.generateFallResponse("No se encuentra el vendedor solicitado");
        return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
    }
}
