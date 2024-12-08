create table leitor (
    id UUID PRIMARY KEY,
    name VARCHAR(100),
    role VARCHAR(255) NOT NULL,
    mail VARCHAR(100) UNIQUE,
    login VARCHAR(50) UNIQUE,
    password VARCHAR(100)--
);

--As senhas desses usuários é senha123
INSERT INTO leitor (id, name, mail, login, password, role) 
VALUES 
    ('d8311b34-5b8b-46b3-8556-5a78f46e39ae', 'João', 'joao@example.com', 'joao123', '$2a$10$aNnnXNRfFTjF3049ALYDyOs7Avrtb04o51CmkcmJ401dagdwEt/9.', 'ADMIN'),
    ('a4e38f33-ff2e-4ff0-8195-91e8794c0b6a', 'Maria', 'maria@example.com', 'maria456', '$2a$10$2WYExYdnoFpn..ZnM5RV7ufCj2zWgTZ25TeBExN44daykJgWhqrDG', 'ADMIN'),
    ('51fb2551-d796-4761-8590-f4fb688f4bdc', 'Pedro', 'pedro@example.com', 'pedro789', '$2a$10$EUTaguwy7bUlDbukJAAe.Oh12/hxL3tVgvlkpMofI9MJUjT84w6am', 'ADMIN'),
    ('f8ec1d7c-7e9d-4d2a-96c3-19a7c671c62d', 'Ana', 'ana@example.com', 'ana123', '$2a$10$EcDppAyop4AxihWOEWufQ.wTmwTFidZK6DliTe1iKcPvnvtnxmr5W', 'ADMIN'),
    ('db48c1fd-8673-4aaf-8376-d0721e31dc09', 'Lucas', 'lucas@example.com', 'lucas456', '$2a$10$xmrVOPi7Gq6WCZFpDXqBUuA1Sux1C/59NceEa00tmzwNrsFT1qYwq', 'ADMIN'),
    ('f9b691e9-0529-40d8-bde3-1fb7283b95b9', 'Carla', 'carla@example.com', 'carla789', '$2a$10$2bAQ3bIgx8u82CKufPGo3.4LRj1baHl2NcQkbMLtbGQoJc9agy9zK', 'ADMIN'),
    ('3b5e3157-2c5e-46b1-b0ac-7d21a0f58da3', 'Marcos', 'marcos@example.com', 'marcos123', '$2a$10$aZkaG6/ezP1ABBPD83h6yuYBDBZDHKnHAOXs7/CT.dx6LJ5e48XuO', 'ADMIN'),
    ('5d6782d9-eb78-4930-8ec2-52e0cb1d49f8', 'Camila', 'camila@example.com', 'camila456', '$2a$10$GHVkOND8ISdumNjYl3TgiuIur3eQBf1HSY5iEbEWqz82rnVSwjW/6', 'ADMIN'),
    ('6ba4d9ac-7f82-4c50-944a-0ab5bf22624f', 'Luciana', 'luciana@example.com', 'luciana789', '$2a$10$sd/yLk0I0DQ7WBcZYFao1eotE2yBShJMaIsTHYI.BujW.9VL54gZu', 'ADMIN'),
    ('8d4c30b2-c5a3-4a15-8b49-04f8680403ef', 'Gabriel', 'gabriel@example.com', 'gabriel123', '$2a$10$DAY90.L4QLtyOmRiOeUMxuIx0Kc87p52tSzIy5jTNu/5QPTWPab0y', 'USER');


