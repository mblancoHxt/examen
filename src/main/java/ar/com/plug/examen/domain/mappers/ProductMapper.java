package ar.com.plug.examen.domain.mappers;

import ar.com.plug.examen.domain.DTOs.ProductDTO;
import ar.com.plug.examen.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product generateProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setPrice(productDTO.getPrice());
        product.setName(productDTO.getName());
        return  product;
    }


    public ProductDTO generateDTO(Product prod) {
        ProductDTO dto = new ProductDTO();
        dto.setId(prod.getId());
        dto.setName(prod.getName());
        dto.setPrice(prod.getPrice());
        return dto;
    }
}
