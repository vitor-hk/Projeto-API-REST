package com.login.user.controllers;

import com.login.user.domain.exceptions.CustomResponse;
import com.login.user.domain.models.Livro;
import com.login.user.domain.dtos.MensagemDto;
import com.login.user.services.LivroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(value = "/livro")
@RestController
public class LivroController {

    @Autowired
    LivroService livroService;

    // GET - Listar todos os livros
    @GetMapping
    public ResponseEntity<List<Livro>> findAll() {
        return ResponseEntity.ok().body(livroService.findAll());
    }

    // GET - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(livroService.findById(id));
    }

    // POST - Inserir livro (já implementado parcialmente)
    @PostMapping
    public ResponseEntity<Livro> insLivro(@RequestBody Livro pLivro) {
        return ResponseEntity.ok().body(livroService.insereLivro(pLivro));
    }

    // POST - Associar livro a um leitor
    @PostMapping("/{idLivro}/leitor/{idLeitor}")
    public ResponseEntity<Livro> associarLivroAoLeitor(@PathVariable Integer idLivro, @PathVariable UUID idLeitor) {
        Livro livroAtualizado = livroService.insUsersLivro(idLivro, idLeitor);
        return ResponseEntity.ok(livroAtualizado);
    }

    // PUT - Atualizar livro
    @PutMapping("/{id}")
    public ResponseEntity<Livro> updLivro(@PathVariable Integer id, @RequestBody Livro pLivro) {
        Livro livroAtualizado = livroService.updLivro(id, pLivro);
        return ResponseEntity.ok().body(livroAtualizado);
    }

    // DELETE - Remover livro
    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemDto> delLivro(@PathVariable Integer id) {
        livroService.delLivro(id);
        return ResponseEntity.ok().body(new MensagemDto("Ok", "Livro " + id + " deletado com sucesso!"));
    }

    // remover um leitor de um livro
    @DeleteMapping("/{idLivro}/leitor/{idLeitor}")
    public ResponseEntity<CustomResponse> delLeitorLivro(@PathVariable Integer idLivro, @PathVariable UUID idLeitor) {
        livroService.delLeitorLivro(idLivro, idLeitor);
        return ResponseEntity.ok(new CustomResponse("OK", "Leitor do livro foi removido com sucesso."));
    }
}
