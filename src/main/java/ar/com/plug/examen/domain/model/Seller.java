package ar.com.plug.examen.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "seller")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seller implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private int dni;
    private String name;
    private String lastname;
    private String addres;
    private String phone;
    private String email;
    private String gender;
    private String password;
    private int status;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Buy> listBuy;


    public <E> Seller(String username, int dni, String name, String lastname, String addres, String phone, String email,
                      String gender, String password, ArrayList<Buy> list) {
        this.username = username;
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.addres = addres;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.listBuy = list;
    }
}

