package com.login.user.domain.dtos;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record UserWithBooksDto(
    UUID id,
    @NotBlank String name,
    @NotBlank String mail,
    @NotBlank String login,
    List<String> livros // lista de títulos dos livros
) {}