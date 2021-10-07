package ar.com.plug.examen.domain.DTOs;

import com.sun.istack.NotNull;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.lang.NonNull;

public class BuyDetailsDTO {
    private int id;
    private Integer id_buy ;
    private Integer id_product;
    private String name_product;
    private Float price_product;
    private Integer cant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getId_buy() {
        return id_buy;
    }

    public void setId_buy(Integer id_buy) {
        this.id_buy = id_buy;
    }

    public Integer getId_product() {
        return id_product;
    }

    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public Float getPrice_product() {
        return price_product;
    }

    public void setPrice_product(Float price_product) {
        this.price_product = price_product;
    }

    public Integer getCant() {
        return cant;
    }

    public void setCant(Integer cant) {
        this.cant = cant;
    }
}
