package ar.com.plug.examen.domain.mappers;

import ar.com.plug.examen.domain.DTOs.ClientDTO;
import ar.com.plug.examen.domain.model.Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientMapper {

    public List<ClientDTO> mappList(List<Client> listClient) {
        ArrayList<ClientDTO> newList = new ArrayList<>();
        for (Client cli : listClient){
            ClientDTO dto = this.generateClientDTO(cli);
            newList.add(dto);
        }
        return newList;
    }

    public ClientDTO generateClientDTO(Client cli) {
        ClientDTO dto = new ClientDTO(cli.getId(), cli.getDni(),cli.getName(),cli.getLastname(),cli.getAddres(),
                cli.getPhone(), cli.getEmail(), cli.getGender(),cli.getUsername(), cli.getPassword());
        return  dto;
    }

    public Client generateClient(ClientDTO request) {
        Client newCli = new Client(request.getId(), request.getDni(), request.getName(), request.getLastname(),
                request.getAddres(), request.getPhone(), request.getEmail(), request.getGender(), request.getUsername(),
                request.getPassword());
        return newCli;
    }
}
