package com.login.user.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.login.user.domain.models.Bibliotecario;
import com.login.user.domain.dtos.BibliotecarioDto;
import com.login.user.domain.dtos.MensagemDto;
import com.login.user.services.BibliotecarioService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(value = "/bibliotecario")
public class BibliotecarioController {

    @Autowired
    private BibliotecarioService bibliotecarioService;

    @GetMapping
    public ResponseEntity<List<BibliotecarioDto>> findAll() {
    List<Bibliotecario> bibliotecarios = bibliotecarioService.findAll();
    List<BibliotecarioDto> response = bibliotecarios.stream()
        .map(bibliotecario -> new BibliotecarioDto(
            bibliotecario.getIdBibliotecario(),
            bibliotecario.getNmBibliotecario(),
            bibliotecario.getEmail(),
            bibliotecario.getSetorBibliotecario().getIdSetor() // Obtendo o ID do setor
        ))
        .collect(Collectors.toList());

    return ResponseEntity.ok().body(response);
}
    
    @GetMapping(value="/{pIdBibliotecario}")
    public ResponseEntity<Bibliotecario> findById(@PathVariable Integer pIdBibliotecario) {
        return ResponseEntity.ok().body(bibliotecarioService.findById(pIdBibliotecario));
    }

    @GetMapping("/setor/{pIdSetor}")
    public ResponseEntity<List<Bibliotecario>> searchBySetor(@PathVariable Integer pIdSetor) {
        return ResponseEntity.ok().body(bibliotecarioService.searchBySetor(pIdSetor));
    }
    
    @GetMapping("/nome/{pNome}")
    public ResponseEntity<List<Bibliotecario>> searchByNome(@PathVariable String pNome) {
        return ResponseEntity.ok().body(bibliotecarioService.searchByNome(pNome));
    }
    
    @PostMapping
    public ResponseEntity<Bibliotecario> insBibliotecario(@RequestBody BibliotecarioDto pBibliotecarioDto) {
    Bibliotecario vNovoBibliotecario = bibliotecarioService.insBibliotecario(pBibliotecarioDto);
    URI vUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/bibliotecario/{pIdBibliotecario}")
            .buildAndExpand(vNovoBibliotecario.getIdBibliotecario())
            .toUri();

    return ResponseEntity.created(vUri).body(vNovoBibliotecario);
}
    
@PutMapping("/{pIdBibliotecario}")
public ResponseEntity<Bibliotecario> updBibliotecario(@PathVariable Integer pIdBibliotecario, @RequestBody BibliotecarioDto pBibliotecarioDto) {
    // Chama o serviço para atualizar o bibliotecário
    Bibliotecario vBibliotecarioAlterado = bibliotecarioService.updBibliotecario(pIdBibliotecario, pBibliotecarioDto);
    
    // Cria a URI para o recurso atualizado
    URI vUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/bibliotecario/{pIdBibliotecario}")
            .buildAndExpand(vBibliotecarioAlterado.getIdBibliotecario())
            .toUri();

    return ResponseEntity.created(vUri).body(vBibliotecarioAlterado);
}

    @DeleteMapping(value="/{pIdBibliotecario}")
    public ResponseEntity<MensagemDto> delBibliotecario(@PathVariable Integer pIdBibliotecario) {
        bibliotecarioService.delBibliotecario(pIdBibliotecario);
        return ResponseEntity.ok().body(new MensagemDto("Ok", "Bibliotecario "+pIdBibliotecario+" deletado com sucesso!"));
    }
}
