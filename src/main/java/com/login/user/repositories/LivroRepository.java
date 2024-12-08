package com.login.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.user.domain.models.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

}
