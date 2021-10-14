package ar.com.plug.examen.domain.DTOs;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SellerDTO {
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
    
}
