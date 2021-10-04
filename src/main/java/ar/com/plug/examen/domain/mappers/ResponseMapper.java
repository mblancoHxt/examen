package ar.com.plug.examen.domain.mappers;

import ar.com.plug.examen.domain.DTOs.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
public class ResponseMapper {


    public ResponseDTO generateResponse( Object data, String resp){
        ResponseDTO response = new ResponseDTO();
        response.setResp(resp);
        response.setData(data);

        return response;
    }

    public ResponseDTO generateFallResponse(String resp){
        ResponseDTO response = new ResponseDTO();
        response.setResp(resp);

        return response;
    }
}
