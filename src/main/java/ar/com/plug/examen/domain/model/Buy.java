package ar.com.plug.examen.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "buy")
public class Buy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_client")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_seller")
    private Seller seller;

    @OneToMany(mappedBy = "buy")
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


}
