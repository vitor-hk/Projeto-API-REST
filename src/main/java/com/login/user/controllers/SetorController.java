package com.login.user.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.login.user.domain.models.Setor;
import com.login.user.services.SetorService;

import jakarta.validation.Valid;

import com.login.user.domain.dtos.SetorDto;
import com.login.user.domain.exceptions.CustomResponse;

@RestController
@RequestMapping(value = "/setor")
public class SetorController {

    @Autowired
    private SetorService setorService;

    @GetMapping
    public ResponseEntity<List<SetorDto>> findAll() {
        List<SetorDto> setores = setorService.findAll();
        
        return ResponseEntity.ok().body(setores);
    }
    
    @GetMapping("/{pIdSetor}")
    public ResponseEntity<Setor> findById(@PathVariable Integer pIdSetor) {
        Setor setor = setorService.findById(pIdSetor);
        return ResponseEntity.ok().body(setor);
    }
    
    @PostMapping
    public ResponseEntity<Setor> insSetor(@Valid @RequestBody Setor pSetor) {
        Setor novoSetor = setorService.insSetor(pSetor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idSetor}").buildAndExpand(novoSetor.getIdSetor()).toUri();
        return ResponseEntity.created(uri).body(novoSetor);
    }
    
    @PutMapping("/{pIdSetor}")
public ResponseEntity<SetorDto> updSetor(@PathVariable Integer pIdSetor, @Valid @RequestBody SetorDto pSetorDto) {
    // Chama o serviço para atualizar o setor
    Setor setorAtualizado = setorService.updSetor(pIdSetor, pSetorDto);
    
    // Retorna a resposta com o setor atualizado
    return ResponseEntity.ok().body(new SetorDto(setorAtualizado.getIdSetor(), setorAtualizado.getNmSetor(), setorAtualizado.getEmail()));
}

    @DeleteMapping("/{idSetor}")
public ResponseEntity<CustomResponse> delSetor(@PathVariable Integer idSetor) {
    try {
        CustomResponse response = setorService.delSetor(idSetor);
        return ResponseEntity.ok(response);
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse("ERROR", e.getMessage()));
    }
}

}