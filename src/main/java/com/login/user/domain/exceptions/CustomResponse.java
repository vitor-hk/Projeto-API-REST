package com.login.user.domain.exceptions;

public class CustomResponse {
    private String status;
    private String mensagem;

    public CustomResponse(String status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}

