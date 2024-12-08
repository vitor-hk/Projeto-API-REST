package com.login.user.domain.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity

@Getter
@Setter
public class Livro implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLivro;
    private String nmLivro;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "livro_leitor",
        joinColumns = @JoinColumn(name = "id_Livro"),
        inverseJoinColumns = @JoinColumn(name = "id_leitor")
    )
    @JsonIgnore
    private List<User> leitores;
}
