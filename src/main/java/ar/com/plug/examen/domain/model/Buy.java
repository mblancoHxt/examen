package ar.com.plug.examen.domain.model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "buy")
public class Buy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_client")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_seller")
    private Seller seller;

    @OneToMany(mappedBy = "buy", cascade= CascadeType.ALL)
    private List<BuyDetails> listDetails;

    private Date date;

    public Buy() {
    }

    public Buy(Integer id, Client client, Seller seller, Date date) {
        this.id = id;
        this.client = client;
        this.seller = seller;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<BuyDetails> getListDetails() {
        return listDetails;
    }

    public void setListDetails(List<BuyDetails> listDetails) {
        this.listDetails = listDetails;
    }
}
