package com.login.user.domain.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
//@Entity(name = "TBL_2120")
@Entity
public class Setor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name="CD_DEPTO")
    private Integer idSetor;
    
    @NotNull(message = "Nome do setor não pode ser nulo!")
    @NotBlank(message = "Nome do setor não pode ser branco!")
    @Length(min = 5, max = 255, message = "Nome do setor deve ter entre 5 e 255 caracteres!")
    private String nmSetor;

    @Email
    private String email;

    @OneToMany(mappedBy="setorBibliotecario")
    private List<Bibliotecario> bibliotecarios = new ArrayList<>();
}
