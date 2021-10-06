package ar.com.plug.examen.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "seller")
@Entity
public class Seller implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
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

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Buy> listBuy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Buy> getListBuy() {
        return listBuy;
    }

    public void setListBuy(List<Buy> listBuy) {
        this.listBuy = listBuy;
    }
}

