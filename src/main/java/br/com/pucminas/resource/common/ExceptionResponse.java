package br.com.pucminas.resource.common;

import java.util.ArrayList;
import java.util.List;

public class ExceptionResponse {

    private List<String> erros = new ArrayList<>();

    public ExceptionResponse(String erro) {
        this.erros.add(erro);
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros.addAll(erros);
    }
}
