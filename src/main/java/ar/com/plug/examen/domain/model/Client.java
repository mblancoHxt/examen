package ar.com.plug.examen.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "Client")
public class Client implements Serializable {

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

    @OneToMany(mappedBy = "client", cascade= CascadeType.ALL)
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
