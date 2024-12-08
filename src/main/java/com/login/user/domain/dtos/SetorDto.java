package com.login.user.domain.dtos;

public class SetorDto {
    private Integer idSetor;
    private String nmSetor;
    private String email;


    public SetorDto(Integer idSetor, String nmSetor, String email) {
        this.idSetor = idSetor;
        this.nmSetor = nmSetor;
        this.email = email;
    }

    public Integer getIdSetor() {
        return idSetor;
    }

    public String getNmSetor() {
        return nmSetor;
    }

    public String getEmail() {
        return email;
    }
}
