# LiterAlura 📚

**LiterAlura** é um projeto desenvolvido como parte do desafio da Alura, com o objetivo de criar uma aplicação Java que consome a API [Gutendex](https://gutendex.com/) para buscar e gerenciar informações de livros e autores, armazenando os dados em um banco de dados PostgreSQL.

A aplicação possui uma interface de linha de comando interativa que permite:

- Buscar livros por título
- Listar livros e autores cadastrados
- Buscar livros por idioma
- Sair do programa

---

## 📌 Funcionalidades Implementadas

### 📖 Menu Interativo (Interface CLI)
A aplicação apresenta 5 opções principais:

1. **Buscar e salvar livro por título**  
   Faz uma requisição à API Gutendex para buscar livros pelo título e salva os resultados no banco de dados.

2. **Listar todos os livros**  
   Exibe todos os livros salvos no banco de dados.

3. **Listar autores**  
   Exibe todos os autores cadastrados no banco.

4. **Buscar livros por idioma**  
   Permite filtrar livros com base no idioma informado (ex.: `en`, `pt`, `es`, etc).

5. **Sair**  
   Encerra a aplicação.

---

## 🌐 Integração com API Gutendex

A aplicação consome a [API Gutendex](https://gutendex.com/) para:

- Buscar livros por título
- Mapear os dados retornados (título, idiomas, número de downloads e autores)
- Popular as entidades `Livro` e `Autor`

---

## 🗄️ Persistência com PostgreSQL

- Utiliza **Spring Data JPA** para salvar e consultar os dados.
- Entidades `Livro` e `Autor` mapeadas com relacionamento **muitos-para-muitos**.
- Banco de dados: `literalura`
- Usuário configurado: `vini`
- Configuração via `application.properties` com uso do dialeto `PostgreSQLDialect`.

---

## 🛠️ Tecnologias Utilizadas

- **Java**: Versão 17 (projeto testado também com Java 22.0.2)
- **Spring Boot**: 3.5.4
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-web`
- **PostgreSQL**: Versão 17
  - Driver JDBC: `postgresql-42.7.7`
- **Jackson**: Para conversão dos dados JSON
- **HttpClient**: Para chamadas HTTP
- **Maven**: Gerenciamento de dependências e build

---

