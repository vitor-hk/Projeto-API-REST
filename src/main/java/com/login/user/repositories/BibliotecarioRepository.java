package com.login.user.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.login.user.domain.models.Bibliotecario;

public interface BibliotecarioRepository extends JpaRepository<Bibliotecario,Integer> {

    @Query(value = "SELECT id_bibliotecario, email, nm_bibliotecario, id_setor FROM bibliotecario WHERE id_setor = :pIdSetor ORDER BY nm_bibliotecario", nativeQuery = true)
    List<Bibliotecario> searchBySetor(@Param("pIdSetor") Integer pIdBibliotecario);

    @Query(value="SELECT id_bibliotecario, email, nm_bibliotecario, id_setor FROM bibliotecario WHERE upper(nm_bibliotecario) LIKE '%'||upper(:pNome)||'%' ORDER BY nm_bibliotecario", nativeQuery = true)
    List<Bibliotecario> searchByNome(@Param("pNome") String pNome);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Bibliotecario b WHERE b.setorBibliotecario.idSetor = :setorId")
    boolean existsBySetorId(@Param("setorId") Integer setorId);

}

