package com.login.user.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class BibliotecarioDto {
    
    @NotBlank(message = "Nome do bibliotecário deve ser informado!")
    private String nmBibliotecario;

    @Email
    private String email;

    private Integer idBibliotecario;

    private Integer pIdSetor; // ID do setor

    

    public BibliotecarioDto() {}


    public BibliotecarioDto(String nmBibliotecario, String email, Integer pIdSetor) {
        this.nmBibliotecario = nmBibliotecario;
        this.email = email;
        this.pIdSetor = pIdSetor;
    }

    // construtor com parâmetros para a resposta
    public BibliotecarioDto(Integer idBibliotecario, String nmBibliotecario, String email, Integer pIdSetor) {
        this.idBibliotecario = idBibliotecario;
        this.nmBibliotecario = nmBibliotecario;
        this.email = email;
        this.pIdSetor = pIdSetor;
    }

    
    public Integer getIdBibliotecario() {
        return idBibliotecario;
    }

    public String getNmBibliotecario() {
        return nmBibliotecario;
    }

    public void setNmBibliotecario(String nmBibliotecario) {
        this.nmBibliotecario = nmBibliotecario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getpIdSetor() {
        return pIdSetor;
    }

    public void setpIdSetor(Integer pIdSetor) {
        this.pIdSetor = pIdSetor;
    }
}