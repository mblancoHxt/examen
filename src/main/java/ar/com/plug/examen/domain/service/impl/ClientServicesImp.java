package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.DTOs.ClientDTO;
import ar.com.plug.examen.domain.DTOs.ResponseDTO;
import ar.com.plug.examen.domain.mappers.ClientMapper;
import ar.com.plug.examen.domain.mappers.ResponseMapper;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ar.com.plug.examen.domain.service.ClientServices;

import java.util.List;

@Service
public class ClientServicesImp implements ClientServices {

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private ResponseMapper responseMapper;

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public ResponseEntity getAllClient() {
        List<Client> listClient = clientRepository.findAll();
        List<ClientDTO> listFinal = clientMapper.mappList(listClient);
        ResponseDTO resp = responseMapper.generateResponse(listClient, "Respuesta correcta");
        return new ResponseEntity(resp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity saveClient(ClientDTO request) {
        Client cliPrueba = clientRepository.findByUsernameEquals(request.getUsername());
        if(cliPrueba == null) {
            Client cli = clientMapper.generateClient(request);
            ClientDTO dto = clientMapper.generateClientDTO(clientRepository.save(cli));
            ResponseDTO resp = responseMapper.generateResponse(dto, "Agregado con éxito");
            return new ResponseEntity(resp, HttpStatus.OK);
        }
        else{
            ResponseDTO resp = responseMapper.generateFallResponse("Ya existe el usuario");
            return  new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity modifyClient(ClientDTO request) {
        Client cli = clientRepository.findByIdEquals(request.getId());
        if(cli.getDni() != request.getDni() || !(cli.getUsername().equals(request.getUsername()))){
            ResponseDTO resp = responseMapper.generateFallResponse("No se pueden cambiar el username ni el dni");
            return  new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
        }
        else{
            Client cliMod = clientMapper.generateClient(request);
            Client cliResp = clientRepository.save(cliMod);
            ClientDTO dto = clientMapper.generateClientDTO(cliResp);
            ResponseDTO resp = responseMapper.generateResponse(dto, "Cliente modificado con éxito");
            return  new ResponseEntity(resp, HttpStatus.OK);
        }

    }

    @Override
    public ResponseEntity deleteClient(Integer id) {
        Client cli = clientRepository.findByIdEquals(id);
        if(cli != null){
            clientRepository.delete(cli);
            ClientDTO cliEl = clientMapper.generateClientDTO(cli);
            ResponseDTO dto = responseMapper.generateResponse(cliEl, "Cliente eliminado con éxito");
            return new ResponseEntity(dto, HttpStatus.OK);
        }
        else{
            ResponseDTO resp = responseMapper.generateFallResponse("No se encuentra el cliente");
            return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity getClient(Integer id) {
        Client cli = clientRepository.findByIdEquals(id);
        if(cli != null){
            ClientDTO cliRet = clientMapper.generateClientDTO(cli);
            ResponseDTO resp = responseMapper.generateResponse(cliRet, "Operación completada con éxito");
            return new ResponseEntity(resp, HttpStatus.OK);
        }
        else{
            ResponseDTO resp = responseMapper.generateFallResponse("No se encuentra el cliente");
            return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
        }
    }
}
