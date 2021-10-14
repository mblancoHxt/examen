package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.DTOs.ClientDTO;
import ar.com.plug.examen.domain.DTOs.ResponseDTO;
import ar.com.plug.examen.domain.mappers.ClientMapper;
import ar.com.plug.examen.domain.mappers.ResponseMapper;
import ar.com.plug.examen.domain.model.Buy;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.repository.ProductRepository;
import org.apache.catalina.Cluster;
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

    @Autowired
    private ProductRepository productRepository;


    @Override
    public ResponseEntity getAllClient() {
        List<Client> listClient = clientRepository.findByStatusGreaterThan(0);
        List<ClientDTO> listFinal = clientMapper.mappList(listClient);
        ResponseDTO resp = responseMapper.generateResponse(listFinal, "Respuesta correcta");
        return new ResponseEntity(resp, HttpStatus.OK);
    }

    @Override
    public ResponseEntity saveClient(ClientDTO request) {
        Client cliPrueba = clientRepository.findByUsernameEquals(request.getUsername());
        if(cliPrueba == null) {
            Client cli = clientMapper.generateClient(request);
            cli.setStatus(1);
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
        if(cli.getStatus() > 0 ) {
            if (cli.getDni() != request.getDni() || !(cli.getUsername().equals(request.getUsername()))) {
                ResponseDTO resp = responseMapper.generateFallResponse("No se pueden cambiar el username ni el dni");
                return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
            } else {
                Client cliMod = clientMapper.generateClient(request);
                Client cliResp = clientRepository.save(cliMod);
                //ClientDTO dto = clientMapper.generateClientDTO(cliResp);
                ResponseDTO resp = responseMapper.generateResponse(request, "Cliente modificado con éxito");
                return new ResponseEntity(resp, HttpStatus.OK);
            }
        }
        else{
            ResponseDTO resp = responseMapper.generateFallResponse("Cliente suspendido o eliminado");
            return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity deleteClient(Integer id) {
        Client cli = clientRepository.findByIdEquals(id);
        if(cli != null) {
            //clientRepository.delete(cli);
            cli.setStatus(0);
            cli = clientRepository.save(cli);
            ClientDTO cliEl = clientMapper.generateClientDTO(cli);
            ResponseDTO dto = responseMapper.generateResponse(cliEl, "Cliente eliminado con éxito.");
            return new ResponseEntity(dto, HttpStatus.OK);
        }
        else{
            ResponseDTO resp = responseMapper.generateFallResponse("No se encuentra el cliente.");
            return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity getClient(Integer id) {
        Client cli = clientRepository.findByIdEquals(id);
        if(cli != null) {
            if (cli.getStatus() > 0) {
                ClientDTO cliRet = clientMapper.generateClientDTO(cli);
                ResponseDTO resp = responseMapper.generateResponse(cliRet, "Operación completada con éxito");
                return new ResponseEntity(resp, HttpStatus.OK);
            } else {
                ResponseDTO resp = responseMapper.generateFallResponse("El cliente está suspendido o eliminado");
                return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
            }
        }
        else{
            ResponseDTO resp = responseMapper.generateFallResponse("No se encuentra el cliente");
            return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity getClientByProduct(Integer id) {
        Product prod = productRepository.findById(id);
        if(prod != null){
            List<Client> listClient = clientRepository.findByListBuy_ListDetails_ProductEquals(prod);
            List<ClientDTO> listResp = clientMapper.mappList(listClient);
            ResponseDTO resp = responseMapper.generateResponse(listResp, "Respuesta correcta");
            return new ResponseEntity(resp, HttpStatus.OK);
        }
        else{
            ResponseDTO resp = responseMapper.generateFallResponse("No se encuentra el cliente");
            return new ResponseEntity(resp, HttpStatus.BAD_REQUEST);
        }
    }
}
