package com.login.user.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.user.domain.dtos.BibliotecarioDto;
import com.login.user.domain.exceptions.CustomResponse;
import com.login.user.domain.exceptions.NoSuchElementException;
import com.login.user.domain.models.Setor;
import com.login.user.domain.models.Bibliotecario;
import com.login.user.repositories.SetorRepository;
import com.login.user.repositories.BibliotecarioRepository;

@Service
public class BibliotecarioService {

    @Autowired
    private BibliotecarioRepository bibliotecarioRepository;

    @Autowired
    private SetorRepository setorRepository;

    public List<Bibliotecario> findAll() {
        return bibliotecarioRepository.findAll();
    }

    public Bibliotecario findById(Integer pIdBibliotecario) {
        Bibliotecario vBibliotecario = bibliotecarioRepository
            .findById(pIdBibliotecario)
            .orElseThrow();

        return vBibliotecario;
    }

    public Bibliotecario insBibliotecario(BibliotecarioDto pBibliotecarioDto) {
    Setor vSetor = setorRepository
            .findById(pBibliotecarioDto.getpIdSetor())
            .orElseThrow(() -> new NoSuchElementException("Setor não encontrado com ID: " + pBibliotecarioDto.getpIdSetor()));

    Bibliotecario pBibliotecario = new Bibliotecario();
    pBibliotecario.setIdBibliotecario(null);
    pBibliotecario.setNmBibliotecario(pBibliotecarioDto.getNmBibliotecario());
    pBibliotecario.setEmail(pBibliotecarioDto.getEmail());
    pBibliotecario.setSetorBibliotecario(vSetor);

    return bibliotecarioRepository.save(pBibliotecario);
}

public Bibliotecario updBibliotecario(Integer pIdBibliotecario, BibliotecarioDto pBibliotecarioDto) {
    // Encontra o bibliotecário existente
    Bibliotecario vBibliotecarioAtual = bibliotecarioRepository.findById(pIdBibliotecario)
            .orElseThrow(() -> new NoSuchElementException("Bibliotecário não encontrado com ID: " + pIdBibliotecario));

    // Atualiza os campos do bibliotecário
    if (pBibliotecarioDto.getNmBibliotecario() != null) {
        vBibliotecarioAtual.setNmBibliotecario(pBibliotecarioDto.getNmBibliotecario());
    }
    if (pBibliotecarioDto.getEmail() != null) {
        vBibliotecarioAtual.setEmail(pBibliotecarioDto.getEmail());
    }
    
    // Se necessário, atualize o setor
    if (pBibliotecarioDto.getpIdSetor() != null) {
        Setor vSetor = setorRepository.findById(pBibliotecarioDto.getpIdSetor())
                .orElseThrow(() -> new NoSuchElementException("Setor não encontrado com ID: " + pBibliotecarioDto.getpIdSetor()));
        vBibliotecarioAtual.setSetorBibliotecario(vSetor);
    }

    // Salva e retorna o bibliotecário atualizado
    return bibliotecarioRepository.save(vBibliotecarioAtual);
}

    public CustomResponse delBibliotecario(Integer pIdBibliotecario) {
        try {
            bibliotecarioRepository.findById(pIdBibliotecario)
                    .orElseThrow(() -> new NoSuchElementException("Bibliotecario não encontrado com ID: " + pIdBibliotecario));
            bibliotecarioRepository.deleteById(pIdBibliotecario);
            return new CustomResponse("OK", "Bibliotecario deletado com sucesso.");
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Erro ao deletar bibliotecario: " + ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("Erro inesperado ao deletar bibliotecario: " + ex.getMessage());
        }
    }
    

    public List<Bibliotecario> searchBySetor(Integer pIdSetor) {

        Setor vSetor = setorRepository
            .findById(pIdSetor)
            .orElseThrow(
                () -> new NoSuchElementException("Setor "+pIdSetor+" não encontrado!")
            );

        List<Bibliotecario> bibliotecarios = bibliotecarioRepository.searchBySetor(pIdSetor);

        if (bibliotecarios.isEmpty()) {
            throw new NoSuchElementException("Setor "+pIdSetor+" - "+vSetor.getNmSetor()+" não possui bibliotecarios!");
        }

        return bibliotecarios;
    }

    public List<Bibliotecario> searchByNome(String pNome) {
        return bibliotecarioRepository.searchByNome(pNome);
    }
}
