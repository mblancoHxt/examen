package ar.com.plug.examen.domain.mappers;

import ar.com.plug.examen.domain.DTOs.BuyDTO;
import ar.com.plug.examen.domain.DTOs.BuyDetailsDTO;
import ar.com.plug.examen.domain.model.Buy;
import ar.com.plug.examen.domain.model.BuyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuyMapper {

    public BuyDTO generateBuyDTO(Buy bResp) {
        BuyDTO dtoResp = new BuyDTO();
        dtoResp.setId(bResp.getId());
        dtoResp.setDate(bResp.getDate());
        dtoResp.setId_client(bResp.getClient().getId());
        dtoResp.setId_seller(bResp.getSeller().getId());
        dtoResp.setLastname_client(bResp.getClient().getLastname());
        dtoResp.setLastname_seller(bResp.getSeller().getLastname());
        dtoResp.setName_client(bResp.getClient().getName());
        dtoResp.setName_seller(bResp.getSeller().getName());
        List<BuyDetails> listDetails = bResp.getListDetails();
        List<BuyDetailsDTO> listDetailsDTO = new ArrayList<>();
        Float tot = dtoResp.getTotal();
        if(listDetails != null) {
            for (BuyDetails det : listDetails) {
                BuyDetailsDTO dto = this.generateBuyDetailDTO(det);
                tot += dto.getPrice_product() * dto.getCant();
                listDetailsDTO.add(dto);
            }
        }
        dtoResp.setTotal(tot);
        dtoResp.setDetails(listDetailsDTO);
        return dtoResp;
    }

    private BuyDetailsDTO generateBuyDetailDTO(BuyDetails details) {
        BuyDetailsDTO dto = new BuyDetailsDTO();
        dto.setId(details.getId());
        dto.setId_buy(details.getBuy().getId());
        dto.setName_product(details.getProduct().getName());
        dto.setId_product(details.getProduct().getId());
        dto.setPrice_product(details.getProduct().getPrice());
        dto.setCant(details.getCant());
        return dto;
    }

    public Buy generateBuy(BuyDTO buyDTO) {
        Buy resp = new Buy();
        resp.setDate(buyDTO.getDate());
        return resp;
    }
}
