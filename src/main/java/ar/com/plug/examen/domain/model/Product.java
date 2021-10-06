package ar.com.plug.examen.domain.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="Product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Float price;

    @OneToMany(mappedBy = "product", cascade= CascadeType.ALL)
    private List<BuyDetails> listDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<BuyDetails> getListDetails() {
        return listDetails;
    }

    public void setListDetails(List<BuyDetails> listDetails) {
        this.listDetails = listDetails;
    }
}
