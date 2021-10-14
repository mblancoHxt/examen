package ar.com.plug.examen.domain.mappers;

import ar.com.plug.examen.domain.DTOs.SellerDTO;
import ar.com.plug.examen.domain.model.Seller;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class SellerMapper {


    public SellerDTO generateDTO(Seller seller) {
        SellerDTO dto = new SellerDTO(seller.getId(), seller.getUsername(), seller.getDni(), seller.getName(),
                seller.getLastname(), seller.getAddres(),seller.getPhone(), seller.getEmail(), seller.getGender(),
                seller.getPassword());
        return dto;
    }

    public Seller generateSeller(SellerDTO sellerDTO) {
        Seller seller = new Seller( sellerDTO.getUsername(), sellerDTO.getDni(), sellerDTO.getName(), sellerDTO.getLastname(),
                sellerDTO.getAddres(), sellerDTO.getPhone(), sellerDTO.getEmail(),sellerDTO.getGender(), sellerDTO.getPassword(),
                new ArrayList<>());
        return seller;

    }
}
