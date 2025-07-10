<img align=left src="https://i.imgur.com/obfZlgE.png" height=150 alt="badge-challenge">

<h2 align=center>Challenge ONE Back End Java + Spring</h2>

<div align=center>

<img height="80" margin="10" src="https://i.imgur.com/9Gq6RS0.png">
</div>

### Sprint 01: Implementando uma API Rest com Spring

<br> 

## História

Bem-vindo ao nosso mais recente desafio Challenge Back End!

O Fórum Alura é um lugar onde todos os alunos da plataforma alura podem colocar suas perguntas sobre determinados
cursos. Este lugar mágico está cheio de muita aprendizagem e colaboração entre alunos, professores e moderadores.

Já sabemos para que serve o fórum e sabemos sua aparência, mas sabemos como funciona por trás? Isto é, onde se armazenam
as informações? Como se tratam esses dados para que se relacione um tópico com uma resposta, ou como se relacionam os
usuários com as respostas de um tópico?

Esse é o nosso desafio, vamos replicar esse processo no nível do back end e, para isso, criaremos uma API REST usando
Spring.

Nossa API se concentrará especificamente nos tópicos, e deve permitir aos usuários:

- Criar um novo tópico
- Mostrar todos os tópicos criados
- Mostrar um tópico específico
- Atualizar um tópico
- Eliminar um tópico


Ao final de nosso processo teremos uma API REST com as seguintes funcionalidades:

1. API com rotas implementadas seguindo as melhores práticas do modelo REST;
2. Validações realizadas segundo as regras de negócio;
3. Implementação de uma base de dados para a persistência da informação;
4. Serviço de autenticação/autorização para restringir o acesso à informação.

<br>

## Resultado

<img src="https://i.imgur.com/AEavqEN.png">

### Principais endpoints

| Method          | URL                                                | Result                                       |
|-----------------|----------------------------------------------------|----------------------------------------------|
| **[GET](#)**    | `http://localhost:8080/topics`                     | Lista todos os Tópicos                       |
| **[GET](#)**    | `http://localhost:8080/topics/id`                  | Lista um Tópico específico detalhado         |
| **[POST](#)**   | `http://localhost:8080/topics`                     | Cadastra um Tópico                           |
| **[PUT](#)**    | `http://localhost:8080/topics/id`                  | Atualiza um Tópico                           |
| **[DELETE](#)** | `http://localhost:8080/topics/id`                  | Deleta um Tópico                             |
| **[GET](#)**    | `http://localhost:8080/topics/id/answers`          | Lista todas as Respostas de um Tópico        |
| **[POST](#)**   | `http://localhost:8080/topics/id/answers`          | Cadastra uma Resposta em um Tópico           |
| **[POST](#)**   | `http://localhost:8080/answers/id/answer_solution` | Define uma Resposta como a solução do Tópico |
| **[POST](#)**   | `http://localhost:8080/api/login`                  | Faz login para receber JWT                   |
| **[POST](#)**   | `http://localhost:8080/api/register`               | Cadastra um novo usuário                     |

### Endpoints adicionados nas melhorias

| Method          | URL                                                | Result                                       |
|-----------------|----------------------------------------------------|----------------------------------------------|
| **[GET](#)**    | `http://localhost:8080/topics/filter`              | Busca tópicos com filtros combinados         |
| **[GET](#)**    | `http://localhost:8080/topics/search?title=texto`  | Busca tópicos por título                     |
| **[GET](#)**    | `http://localhost:8080/topics/category/{id}`       | Busca tópicos por categoria                  |
| **[GET](#)**    | `http://localhost:8080/topics/course/{id}`         | Busca tópicos por curso                      |
| **[GET](#)**    | `http://localhost:8080/topics/status/{status}`     | Busca tópicos por status                     |
| **[GET](#)**    | `http://localhost:8080/topics/date-range`          | Busca tópicos por período de data            |

### Melhorias implementadas

1. **Documentação da API com Swagger/OpenAPI**
   - Documentação completa dos endpoints
   - Interface interativa para teste da API
   - Acesse em: `http://localhost:8080/swagger-ui.html`

2. **Tratamento de erros aprimorado**
   - Mensagens de erro mais descritivas e padronizadas
   - Tratamento de exceções específicas
   - Respostas de erro com mais informações

3. **Busca avançada com filtros**
   - Filtros por título, categoria, curso e status
   - Filtros por período de data
   - Combinação de múltiplos filtros em uma única consulta

<br>

### Tecnologias utilizadas

- Linguagem: Java
- IDE: <a href="https://www.jetbrains.com/idea/">IntelliJ IDEA</a>
- Framework: Spring
- Spring Data JPA, Spring Boot Starter Web, Mysql, Flyway, Lombok, Spring Validation, Spring Boot
  Security, <a href="https://github.com/auth0/java-jwt">java-jwt</a>

<br><br>
[<img align="left" height="50" margin="10" src="https://i.imgur.com/RYYUpCK.png">](https://www.oracle.com/br/education/oracle-next-education/)
API REST de um **Fórum** desenvolvido como Challenge, durante a formação Backend Java, do
programa <a href="https://www.oracle.com/br/education/oracle-next-education/">ONE (Oracle Next Education)</a> através da
plataforma de ensino <a href="https://www.alura.com.br/">Alura</a>.
tmj aluura
