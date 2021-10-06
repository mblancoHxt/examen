package ar.com.plug.examen.domain.model;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.checkerframework.checker.index.qual.GTENegativeOne;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "buydetails")
public class BuyDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBuy", referencedColumnName = "id")
    private Buy buy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProduct", referencedColumnName = "id")
    private Product product;

    private int cant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Buy getBuy() {
        return buy;
    }

    public void setBuy(Buy buy) {
        this.buy = buy;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
}
