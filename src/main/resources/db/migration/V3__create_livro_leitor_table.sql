CREATE TABLE livro_leitor (
    id_leitor UUID NOT NULL,
    id_livro INTEGER NOT NULL,
    PRIMARY KEY (id_leitor, id_livro),
    FOREIGN KEY (id_leitor) REFERENCES leitor(id),
    FOREIGN KEY (id_livro) REFERENCES livro(id_livro)
);