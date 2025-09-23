## 🏦 Teste Banco Carrefour - API REST

API desenvolvida como parte de um desafio técnico para o Banco Carrefour, utilizando Java, RestAssured e práticas de automação de testes REST.

## 📌 Objetivo

Criar e testar endpoints RESTful relacionados à gestão de usuários, simulando um ambiente de autenticação, cadastro, listagem, atualização e exclusão de dados.

## 🚀 Tecnologias Utilizadas

Java 11+

Maven

JUnit 5

RestAssured

JSON

IntelliJ IDEA (recomendado)

## 🧪 Testes Automatizados

Todos os testes são realizados contra a API pública Serverest ,com os seguintes casos de uso:

✅ Login

Autentica e retorna o token (Bearer) de acesso necessário para os demais testes.

✅ Criar Usuário Válido

Cria um novo usuário com e-mail dinâmico, valida a resposta com status 201 e mensagem de sucesso.

✅ Listar Usuários

Faz uma requisição GET /usuarios autenticada, esperando uma lista não vazia de usuários.

✅ Buscar Usuário por ID

Consulta um usuário específico por seu ID (pré-definido no teste), e valida o status de retorno 200.

✅ Criar e Atualizar Usuário

Cria um novo usuário.

Atualiza seus dados (nome, e-mail, senha, etc.).

Valida se o retorno contém a mensagem "Registro alterado com sucesso".

✅ Criar e Deletar Usuário

Cria um usuário temporário.

Deleta esse usuário.

Valida se o retorno contém a mensagem "Registro excluído com sucesso".

## 🛠️ Como Executar os Testes

Clone o repositório:

git clone https://github.com/RodolfoDodo/Teste-Banco-Carrefour-Opah-API.git
cd Teste-Banco-Carrefour-Opah-API

## Execute os testes com Maven:

mvn clean test


Certifique-se de que o Maven e o JDK estão corretamente instalados em sua máquina.

## 🗂️ Requisitos

Java 11 ou superior

Maven instalado

Acesso à internet (a API Serverest é online)

## 📃 Observações

Os testes dependem de uma API externa pública (https://serverest.dev).

O e-mail dos usuários é gerado dinamicamente com base em System.currentTimeMillis() para evitar duplicações.

O token de autenticação é obtido via POST /login e usado em todos os testes subsequentes.

## 👤 Autor

Rodolfo Dodo

Este projeto é parte de um teste técnico com fins educacionais e demonstrativos.
