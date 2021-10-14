package ar.com.plug.examen.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Client")
public class Client implements Serializable {

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

    @OneToMany(mappedBy = "client", cascade= CascadeType.REMOVE)
    private List<Buy> listBuy;

    public Client() {
    }

    public Client(int id, int dni, String name, String lastname, String addres, String phone, String email, String gender, String username, String password) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.addres = addres;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.username = username;
        this.password = password;
    }

    public Client(String username, int dni, String name, String lastname, String addres, String phone, String email, String gender, String password) {
        this.username = username;
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.addres = addres;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.password = password;
    }
}
