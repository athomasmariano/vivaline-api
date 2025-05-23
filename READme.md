# 🚉 Totem Vivaline API

Sistema backend em Java para gerenciamento e visualização das estações da rede ferroviária Vivaline. Esta API RESTful foi desenvolvida com foco educacional e utiliza Jakarta REST, DTOs e boas práticas de desenvolvimento para fornecer uma estrutura clara e funcional.

## 📚 Descrição

Este projeto simula o backend de um totem informativo que exibe dados sobre estações de trem/metrô, incluindo nome da estação, coordenadas geográficas e as linhas disponíveis. A API permite:

- Listar todas as estações.
- Adicionar uma nova estação.
- Deletar estações específicas.

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Jakarta RESTful Web Services (JAX-RS)
- MicroProfile (Rate Limit, Timeout, Fallback)
- RESTEasy Reactive
- DTO Pattern

## 📁 Estrutura do Projeto

```plaintext
fiap.tds/
├── dtos/
│   └── MapaDto.java
├── services/
│   └── MapaService.java
├── CardResource.java
└── MapaResource.java
```

# 🚀 Como usar

## 📦 Requisitos
JDK 17+

Maven ou Gradle

IDE como IntelliJ ou Eclipse

## ▶️ Execução
### Clone o repositório:
```plaintext
git clone https://github.com/athomasmariano/Vivaline-API-RESTful
cd Vivaline-API-RESTful
```
### Compile e execute com sua IDE ou via terminal:
```plaintext
mvn clean install
```

### Acesse os endpoints via Postman ou navegador.

## 📡 Endpoints da API

### 📍 /faq

#### POST

Requisição: application/json

Resposta: application/json

Descrição: Envia uma pergunta ou contato para a FAQ.

#### GET

Requisição: application/json

Resposta: application/json

Descrição: Obtém a lista de perguntas frequentes.


### 📍 /comercios
#### POST

Requisição: application/json

Resposta: application/json

Descrição: Adiciona um novo comércio.

#### GET
Requisição: application/json

Resposta: application/json

Descrição: Obtém a lista de todos os comércios.

#### DELETE /comercios/{nome}

Requisição: Não exige corpo

Resposta: application/json

Descrição: Deleta um comércio específico pelo nome.

### 📍 /linhas
#### POST

Requisição: application/json

Resposta: application/json

Descrição: Adiciona uma nova linha.

#### GET
Requisição: Não exige corpo
Resposta: application/json
Descrição: Obtém a lista de todas as linhas.

#### DELETE /linhas/{id}
Requisição: Não exige corpo
Resposta: application/json
Descrição: Deleta uma linha específica pelo ID.

#### PUT /linhas/{id}
Requisição: application/json
Resposta: application/json
Descrição: Atualiza as informações de uma linha específica pelo ID.

### 📍 /mapa
#### POST
Requisição: application/json
Resposta: application/json
Descrição: Adiciona uma nova estação ao mapa.

#### GET
Requisição: Não exige corpo
Resposta: application/json
Descrição: Obtém todas as estações cadastradas.

#### DELETE /mapa/{nome}
Requisição: Não exige corpo
Resposta: application/json
Descrição: Deleta uma estação específica pelo nome.

## 📦 Exemplo de Payload (POST /mapa)
{
"nomeEstacao": "Estação Sé",

"latitude": -23.5503,

"longitude": -46.6339,

"linhas": ["Linha Vermelha", "Linha Azul"]

}

# 👨‍💻 Autor
### Arthur Thomas Mariano de Souza
####
Estudante de Análise e Desenvolvimento de Sistemas | FIAP

#### 📫 LinkedIn: https://www.linkedin.com/in/arthur-thomas-mariano-941a97234/