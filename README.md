# ChallangeBD
API REST - Tópicos
Este projeto implementa uma API REST usando o Spring Boot. A API permite a criação, leitura, atualização e exclusão (CRUD) de tópicos.

Funcionalidades
A API possui as seguintes funcionalidades:

Criar um novo tópico.
Listar todos os tópicos.
Consultar um tópico específico por ID.
Atualizar um tópico.
Excluir um tópico.
Tecnologias Utilizadas
Java 11 ou superior
Spring Boot (versão 2.7.x ou superior)
Spring Web: Para criação da API REST.
Spring Data JPA: Para persistência no banco de dados.
Banco de Dados H2 (em memória, usado para desenvolvimento).
Spring Boot DevTools: Para facilitar o desenvolvimento.
Spring Security (caso queira adicionar autenticação posteriormente).
Como Rodar o Projeto
Passo 1: Clonar o Repositório
Clone o repositório para o seu ambiente local:

bash
Copiar
git clone https://github.com/seu-usuario/api-topicos.git
Passo 2: Abrir o Projeto no IntelliJ
Abra o IntelliJ IDEA.
Selecione Open e escolha o diretório do projeto clonado.
Passo 3: Configuração do Banco de Dados
Este projeto está configurado para usar o H2 Database em memória, o que significa que não é necessário configurar um banco de dados externo. O H2 será inicializado automaticamente quando você rodar a aplicação.

Caso queira usar outro banco de dados (como MySQL ou PostgreSQL), edite as configurações no arquivo application.properties.

Passo 4: Rodar a Aplicação
No IntelliJ, abra a classe ApiTopicosApplication.java.
Clique em Run (ou use o atalho Ctrl + Shift + F10).
Isso irá iniciar o servidor Spring Boot na porta 8080.

Endpoints da API
1. Listar todos os tópicos
Método HTTP: GET
URL: /topics
Descrição: Retorna todos os tópicos cadastrados.
Exemplo de requisição:

bash
Copiar
GET http://localhost:8080/topics
2. Buscar um tópico pelo ID
Método HTTP: GET
URL: /topics/{id}
Descrição: Retorna um tópico específico pelo ID.
Exemplo de requisição:

bash
Copiar
GET http://localhost:8080/topics/1
3. Criar um novo tópico
Método HTTP: POST
URL: /topics
Descrição: Cria um novo tópico. Os dados devem ser enviados no corpo da requisição em formato JSON.
Exemplo de requisição:

bash
Copiar
POST http://localhost:8080/topics
Body:

json
Copiar
{
  "title": "Introdução ao Spring Boot",
  "description": "Tutorial básico sobre Spring Boot"
}
4. Atualizar um tópico existente
Método HTTP: PUT
URL: /topics/{id}
Descrição: Atualiza os dados de um tópico existente.
Exemplo de requisição:

bash
Copiar
PUT http://localhost:8080/topics/1
Body:

json
Copiar
{
  "title": "Introdução ao Spring Boot Atualizado",
  "description": "Tutorial atualizado sobre Spring Boot"
}
5. Excluir um tópico
Método HTTP: DELETE
URL: /topics/{id}
Descrição: Exclui um tópico pelo ID.
Exemplo de requisição:

bash
Copiar
DELETE http://localhost:8080/topics/1
Testando a API
Você pode testar os endpoints da API utilizando ferramentas como:

Postman: Para fazer requisições HTTP de forma interativa.
cURL: Para realizar requisições via linha de comando.
Swagger UI (se você adicionar o SpringFox ou Springdoc no futuro para documentação interativa).
Estrutura do Projeto
plaintext
Copiar
api-topicos
│
├── src/main/java/com/exemplo/api
│   ├── ApiTopicosApplication.java  (Classe principal do Spring Boot)
│   ├── controller                 (Pacote com os controladores REST)
│   ├── model                      (Pacote com as entidades)
│   ├── repository                 (Pacote com o repositório JPA)
│   └── service                    (Pacote com a lógica de negócio)
│
└── src/main/resources
    └── application.properties     (Configurações da aplicação)
Dependências
Spring Boot Starter Web: Para criar APIs REST.
Spring Boot Starter Data JPA: Para interagir com o banco de dados.
Spring Boot Starter Security: Para autenticação (opcional).
H2 Database: Banco de dados em memória para desenvolvimento.
Spring Boot DevTools: Ferramentas para facilitar o desenvolvimento.
Licença
Esse projeto é licenciado sob a MIT License - veja o arquivo LICENSE para mais detalhes.
