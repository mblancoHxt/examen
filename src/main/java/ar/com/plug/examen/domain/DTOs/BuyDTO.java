package ar.com.plug.examen.domain.DTOs;

import ar.com.plug.examen.domain.model.BuyDetails;

import java.util.Date;
import java.util.List;

public class BuyDTO {
    private int id;
    private int id_client;
    private String name_client;
    private String lastname_client;
    private int id_seller;
    private String name_seller;
    private String lastname_seller;
    private Date date;
    private List<BuyDetailsDTO> details;
    private Float total = new Float(0.0);


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getName_client() {
        return name_client;
    }

    public void setName_client(String name_client) {
        this.name_client = name_client;
    }

    public String getLastname_client() {
        return lastname_client;
    }

    public void setLastname_client(String lastname_client) {
        this.lastname_client = lastname_client;
    }

    public int getId_seller() {
        return id_seller;
    }

    public void setId_seller(int id_seller) {
        this.id_seller = id_seller;
    }

    public String getName_seller() {
        return name_seller;
    }

    public void setName_seller(String name_seller) {
        this.name_seller = name_seller;
    }

    public String getLastname_seller() {
        return lastname_seller;
    }

    public void setLastname_seller(String lastname_seller) {
        this.lastname_seller = lastname_seller;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<BuyDetailsDTO> getDetails() {
        return details;
    }

    public void setDetails(List<BuyDetailsDTO> details) {
        this.details = details;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
