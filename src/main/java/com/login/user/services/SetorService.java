package com.login.user.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.login.user.domain.dtos.SetorDto;
import com.login.user.domain.exceptions.CustomResponse;
import com.login.user.domain.exceptions.NoSuchElementException;
import com.login.user.domain.models.Setor;
import com.login.user.repositories.BibliotecarioRepository;
import com.login.user.repositories.SetorRepository;

@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
        private BibliotecarioRepository bibliotecarioRepository; // Certifique-se de que esta instância está injetada


    // Listar todos os setores
    public List<SetorDto> findAll() {
        List<Setor> setores = setorRepository.findAll();
        List<SetorDto> setorDtos = setores.stream()
                                          .map(setor -> new SetorDto(setor.getIdSetor(), setor.getNmSetor(), setor.getEmail()))
                                          .collect(Collectors.toList());

        if (setorDtos.isEmpty()) {
            throw new NoSuchElementException("Nenhum setor encontrado");
        }

        return setorDtos;
    }

    

    // Buscar setor por ID
    public Setor findById(Integer id) {
        return setorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Setor não encontrado com ID: " + id));
    }

    // Inserir novo setor
    public Setor insSetor(Setor setor) {
    // Não defina o ID como null, permita que o ID fornecido seja usado
    // Verifique se o ID já existe
    if (setorRepository.existsById(setor.getIdSetor())) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "ID já existe");
    }
    
    return setorRepository.save(setor);
}

    // Atualizar setor
    public Setor updSetor(Integer id, SetorDto setorDto) {
        // Busca o setor existente pelo ID
        Setor setorExistente = setorRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Setor não encontrado com ID: " + id));
    
        // Atualiza os campos do setor
        setorExistente.setNmSetor(setorDto.getNmSetor());
        setorExistente.setEmail(setorDto.getEmail());
    
        // Salva as alterações no banco de dados
        return setorRepository.save(setorExistente);
    }

    // Deletar setor
    public CustomResponse delSetor(Integer id) {
        try {
            if (bibliotecarioRepository.existsBySetorId(id)) {
                throw new RuntimeException("Não é possível deletar o setor, pois existem bibliotecários associados a ele.");
            }
    
            Setor setor = findById(id);
            setorRepository.delete(setor);
            return new CustomResponse("OK", "Setor deletado com sucesso.");
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Erro ao deletar: " + ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("Erro inesperado ao deletar setor: " + ex.getMessage());
        }
    }
}