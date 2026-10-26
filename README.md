# GameStorage

Sistema web de controle de estoque de produto.
**Nome: Mateus Nascimento Ferreira de Souza

## Funcionalidades
- Cadastrar, editar e excluir produtos
- Pesquisar produtos e filtrar por Hardware ou Periférico
- Login de usuários (gerente e funcionário), com senha criptografada
- Cálculo do valor total do estoque e do preço com desconto
- Tema claro e escuro

## Tecnologias
- Java 17 ou mais novo
- Spring Boot 3.5 (API REST)
- Spring Data JPA
- MySQL 8
- HTML, CSS e JavaScript
- JUnit 5 e H2 (testes)
- Maven e NetBeans
- Git e GitHub


Usuários de teste (criados na primeira execução):

| Usuário | Senha | Tipo |
|---|---|---|
| admin | 1234 | gerente |
| func | 1234 | funcionario |

## Testes
No NetBeans, clique com o botão direito no projeto e escolha **Test**. Os testes de serviço usam um banco H2 em memória, sem mexer no MySQL.

## Estrutura
- `model`: entidades `Produto` e `Usuario`
- `repository`: acesso ao banco (JPA)
- `service`: regras de negócio (`ProdutoService` e `ProdutoValidator`)
- `controller`: API REST
- `exception`: erros de validação e de produto não encontrado
- `config`: carga inicial dos usuários
- `src/main/resources/static`: páginas HTML, CSS e JavaScript
- `src/test`: testes JUnit

## API
| Método | Endereço | O que faz |
|---|---|---|
| GET | /api/produtos | Lista os produtos (aceita `?busca=`) |
| GET | /api/produtos/{id} | Busca um produto |
| POST | /api/produtos | Cadastra um produto |
| PUT | /api/produtos/{id} | Edita um produto |
| DELETE | /api/produtos/{id} | Exclui um produto |
| GET | /api/produtos/total-estoque | Soma de preço vezes estoque |
| POST | /api/login | Confere nome e senha |
