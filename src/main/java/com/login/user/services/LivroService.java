package com.login.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.user.domain.models.Livro;
import com.login.user.domain.models.User;
import com.login.user.repositories.LivroRepository;
import com.login.user.repositories.UsersRepository;
import com.login.user.domain.exceptions.NoSuchElementException;
import com.login.user.domain.exceptions.CustomResponse;
import com.login.user.domain.exceptions.DataIntegrityViolationException;
import com.login.user.domain.exceptions.BookAlreadyRentedException;


import java.util.List;
import java.util.UUID;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsersRepository usersRepository;

    // Criar novo livro
    public Livro insereLivro(Livro pLivro) {
        pLivro.setIdLivro(null);
        return livroRepository.save(pLivro);
    }


    public List<Livro> findAll() {
        return livroRepository.findAll();
    }


    public Livro findById(Integer id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Livro não encontrado com ID: " + id));
    }


    public Livro updLivro(Integer id, Livro pLivro) {
        Livro livroExistente = findById(id); // garantir que o livro existe
        livroExistente.setNmLivro(pLivro.getNmLivro()); 
        return livroRepository.save(livroExistente);
    }


    public CustomResponse delLivro(Integer id) {
        try {
            Livro livro = findById(id); 
            livroRepository.delete(livro);
            return new CustomResponse("OK", "livro deletada com sucesso.");
        } catch (NoSuchElementException ex) {
            
            throw new NoSuchElementException("Erro ao deletar: " + ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException("Erro inesperado ao deletar livro: " + ex.getMessage());
        }
    }


    public Livro insUsersLivro(Integer pIdLivro, UUID pIdUser) {
        User user = usersRepository.findById(pIdUser)
                .orElseThrow(() -> new NoSuchElementException("Users não encontrado com ID: " + pIdUser));

        Livro vLivro = livroRepository.findById(pIdLivro)
                .orElseThrow(() -> new NoSuchElementException("Livro não encontrada com ID: " + pIdLivro));

        List<User> vUsers = vLivro.getLeitores();
        if (vUsers.contains(user)) {
            throw new DataIntegrityViolationException("Users " + user.getName()
                    + " já está alugando o livro " + vLivro.getNmLivro() + "!");
        }
        vUsers.add(user);
        vLivro.setLeitores(vUsers);

        try {
            return livroRepository.save(vLivro);
        } catch (DataIntegrityViolationException e) {
            throw new BookAlreadyRentedException("O leitor " + user.getName() + " já está alugando o livro " + vLivro.getNmLivro() + ".");
        }
    }

    //deletar Users de um livro 
    public Livro delLeitorLivro(Integer pIdLivro, UUID pIdUsers) {
        User vUsers = usersRepository.findById(pIdUsers)
                .orElseThrow(() -> new NoSuchElementException("Users não encontrado com ID: " + pIdUsers));

        Livro vLivro = livroRepository.findById(pIdLivro)
                .orElseThrow(() -> new NoSuchElementException("Livro não encontrada com ID: " + pIdLivro));

        List<User> vUserses = vLivro.getLeitores();
        if (!vUserses.contains(vUsers)) {
            throw new DataIntegrityViolationException("Users " + vUsers.getName()
                    + " não está alugando o livro " + vLivro.getNmLivro() + "!");
        }
        vUserses.remove(vUsers); // remove o leitor da lista
        vLivro.setLeitores(vUserses);

        return livroRepository.save(vLivro);
    }
}
