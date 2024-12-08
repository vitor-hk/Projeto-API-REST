package com.login.user.domain.models;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Bibliotecario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBibliotecario;

    @NotBlank(message="Nome do biblitecario(a) deve ser informado!")
    @Length(min = 5, max = 255, message="Nome do biblitecario(a) deve ter entre 5 e 255 caracteres!")
    private String nmBibliotecario;

    @Email
    private String email;

    @ManyToOne
    @JoinColumn(name="id_setor")
    @JsonIgnore
    private Setor setorBibliotecario;
}
