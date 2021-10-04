package ar.com.plug.examen.domain.DTOs;


public class ResponseDTO {
    private Object data;
    private String resp;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }
}
