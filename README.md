# Projeto-API-REST

API REST utilizando Spring Boot, Spring Security e Docker para gerenciar usuários e dados de uma biblioteca. A aplicação oferece funcionalidades completas de CRUD 
(Criar, Ler, Atualizar e Deletar) para todas as tabelas.

## Instruções de Configuração

### Pré-requisitos

- Docker
- Docker Compose
- Git

### Inicialização do Servidor

1. No terminal, navegue até a pasta onde deseja clonar o projeto e execute os seguintes comandos:

```bash
git clone https://github.com/vitor-hk/Projeto-API-REST.git
cd Projeto-API-REST
```
3. Na pasta root do projeto, execute os seguintes comando:

```bash
docker-compose up`
```
Caso esteja em uma versão mais recente do docker;
```bash
docker compose up`
```
Comando para iniciar a api.

4. Se a porta 8080 não estiver livre, o servidor escolherá automaticamente outra porta. Lembre-se de atualizar as rotas para refletir a nova porta.

## Documentação da API

### Registro de Usuário

```bash
curl -X POST "http://localhost:8080/leitor/register" -H 'Content-Type: application/json' -d '{
"name": "examplo",
"mail": "exemplo@exemplo.com",
"login": "examploLogin",
"password": "password123" }' | jq '.'
```

### Login de Usuário e Obtenção do token
```bash
token=$(curl -X POST "http://localhost:8080/leitor/login" -H 'Content-Type: application/json' -d '{
"login": "ExemploLogin",
"password": "exemploSenha" }' | jq -r '.token')
```
#### A senha padrão dos usuarios criados pelo flyway é: senha123

### Obter Todos os Usuários

```bash
curl -X GET "localhost:8080/leitor/all" -H "Authorization: Bearer $token" | jq '.'
```
### Obter Usuário
```bash
curl -X GET "http://localhost:8080/leitor/UUID do leitor" -H "Authorization: Bearer $token" | jq '.'
```
### Atualizar Usuário
```bash
curl -X PUT "http://localhost:8080/leitor/UUID do leitor" -H 'Content-Type: application/json' -H "Authorization: Bearer $token" -d '{ "name": "examploUsuarioUpdated",
"mail": "exemploUpdated@example.com",
"login": "exemploUpdate",
"password": "novaSenha" }'
```
- A atualização dos dados cadastrais implica na necessidade de um novo login

### Excluir Usuário
```bash
curl -X DELETE "http://localhost:8080/leitor/UUID do leitor" -H "Authorization: Bearer $token"
```
### Excluir Todos os Usuários
```bash
curl -X DELETE "http://localhost:8080/leitor/delete_all" -H "Authorization: Bearer $token"
```
A exclusão de todos os usuários implica na invalidação do token de acesso atualmente em uso. Para verificar se a exclusão foi realizada com sucesso, é necessário cadastrar um novo usuário e utilizar o token gerado para confirmar a operação.
