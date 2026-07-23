# Movie Catalog

> Console application for looking up movies, series, seasons, and episodes with data provided by the OMDb API.

[English](#english) · [Português](#português)

---

## English

### About the project

Movie Catalog is an interactive command-line application built with Java and Spring Boot. After startup, it displays a text menu where the user can search for a movie or a series by title.

The application sends requests to the [OMDb API](https://www.omdbapi.com/), receives JSON responses, converts them into Java records with Jackson, and prints selected catalog information in the terminal.

Despite the artifact name `movie-catalog-api`, this project does **not** expose REST or HTTP endpoints. Spring Boot is used to initialize the application and execute its console workflow through `CommandLineRunner`.

### Features

- Interactive terminal menu in Portuguese.
- Movie lookup by title.
- Display of a movie's title, year, IMDb rating, and release date.
- Series lookup by title.
- Display of a series' title, total number of seasons, and IMDb rating.
- Retrieval of season and episode data from OMDb.
- Menu loop for performing multiple searches without restarting the application.
- Validation of menu input to ensure that an integer is entered.
- Mapping of external JSON fields to immutable Java records.

### How it works

The application follows this flow:

1. Spring Boot starts `MovieCatalogApiApplication`.
2. The application invokes `Menu.mainMenu()` through `CommandLineRunner`.
3. The user chooses one of three options:
   - `1`: search for a movie;
   - `2`: search for a series;
   - `3`: exit.
4. The entered title is normalized by replacing spaces with `+`.
5. `ApiReader` creates an HTTP request with Java's `HttpClient` and sends it to OMDb.
6. `ConvertData` uses Jackson's `ObjectMapper` to deserialize the JSON response into the appropriate record.
7. The mapped data is printed in the terminal.
8. After a search, the menu is shown again until the user exits.

For a series search, the application first obtains its general information and total number of seasons. It then requests every season from OMDb and stores the resulting `SeasonDTO` objects in memory. The user is subsequently prompted to select a season.

### Data models

| Record | OMDb data represented |
| --- | --- |
| `MovieDTO` | Title, year, IMDb rating, and release date |
| `SeriesDTO` | Title, total seasons, and IMDb rating |
| `SeasonDTO` | Season number and list of episodes |
| `EpisodeDTO` | Title, episode number, IMDb rating, and release date |

The records use `@JsonAlias` to map OMDb field names and `@JsonIgnoreProperties(ignoreUnknown = true)` so fields that are not used by the application do not prevent deserialization.

### Technologies

- Java 21
- Spring Boot 4.1.0
- Maven / Maven Wrapper
- Jackson Databind 2.22.1
- Java HTTP Client
- JUnit 5 and Spring Boot Test
- OMDb API

### Requirements

Before running the project, make sure you have:

- Java Development Kit (JDK) 21 installed;
- internet access, because catalog data is retrieved at runtime;
- a valid [OMDb API key](https://www.omdbapi.com/apikey.aspx).

You do not need to install Maven globally because the repository includes the Maven Wrapper.

### Configuration

The repository contains a `.env` file with `OMDB_API_URL` and `OMDB_API_KEY` entries. However, the current source code does **not** load those environment variables: the OMDb URL and API key are declared directly in `Menu.java`.

For a local key, replace the API key used by the `API_KEY` constant in:

```text
src/main/java/br/com/moviecatalog/movie_catalog_api/main/Menu.java
```

The value passed to OMDb must have the following format:

```text
&apikey=YOUR_API_KEY
```

Do not commit a real API key to version control. A recommended future improvement is to read the key from an environment variable or Spring configuration and keep `.env` ignored.

### Running the application

On Linux or macOS:

```bash
./mvnw spring-boot:run
```

On Windows:

```bat
mvnw.cmd spring-boot:run
```

Alternatively, build and run the packaged JAR:

```bash
./mvnw clean package
java -jar target/movie-catalog-api-0.0.1-SNAPSHOT.jar
```

### Using the menu

After startup, enter the number corresponding to the desired operation:

```text
1 - Search for movies
2 - Search for series
3 - Exit
```

For a movie, type its title and the application will display its basic catalog information.

For a series, type its title. The application displays the series information, retrieves all available seasons, and asks which season should be shown. Searches are title-based and depend on the result selected by OMDb for the `t` parameter.

### Running the tests

On Linux or macOS:

```bash
./mvnw test
```

On Windows:

```bat
mvnw.cmd test
```

The current test suite contains a Spring context-loading test.

### Project structure

```text
src
├── main
│   ├── java/br/com/moviecatalog/movie_catalog_api
│   │   ├── MovieCatalogApiApplication.java  # Application entry point
│   │   ├── dto                              # OMDb response records
│   │   ├── main/Menu.java                   # Console workflow and menu
│   │   ├── service
│   │   │   ├── ApiReader.java               # HTTP requests
│   │   │   └── ConvertData.java             # JSON deserialization
│   │   └── utils/InputReader.java           # Terminal input handling
│   └── resources/application.properties     # Spring application name
└── test
    └── java/.../MovieCatalogApiApplicationTests.java
```

### Error handling and current limitations

- No REST endpoints are exposed.
- Search results are not persisted; all data exists only during the current execution.
- The application depends on the OMDb service and an active internet connection.
- OMDb error responses, non-success HTTP status codes, timeouts, and invalid or missing titles are not handled explicitly.
- HTTP or JSON processing failures are converted into unchecked `RuntimeException`s.
- The API key is currently embedded in the source instead of being read from external configuration.
- During a series search, all seasons are fetched eagerly, which may generate several sequential HTTP requests.
- The current season-display filter always selects season `1`, regardless of the season entered by the user.
- The season validation message is currently printed when the entered season is valid rather than invalid.
- The input utility supports integers and decimals, although decimal input is not used by the current menu.
- Automated coverage is currently limited to verifying that the Spring context starts.

### Possible improvements

- Load the OMDb URL and API key from environment variables or Spring properties.
- Correct season validation and display the season selected by the user.
- Add friendly handling for missing titles, invalid keys, rate limits, connection failures, and malformed responses.
- Validate HTTP status codes and preserve thread interruption in `ApiReader`.
- Fetch only the requested season instead of loading every season in advance.
- Add unit and integration tests for menu choices, data conversion, and external API failures.
- Introduce dependency injection for services to improve testability.
- Add caching or persistence for previously retrieved titles.

---

## Português

### Sobre o projeto

Movie Catalog é uma aplicação interativa de linha de comando desenvolvida com Java e Spring Boot. Após a inicialização, ela exibe um menu de texto no qual o usuário pode pesquisar um filme ou uma série pelo título.

A aplicação envia requisições para a [API OMDb](https://www.omdbapi.com/), recebe respostas em JSON, converte os dados em records Java com o Jackson e apresenta no terminal as informações selecionadas do catálogo.

Apesar do nome do artefato ser `movie-catalog-api`, este projeto **não** disponibiliza endpoints REST ou HTTP. O Spring Boot é utilizado para inicializar a aplicação e executar o fluxo de terminal por meio de `CommandLineRunner`.

### Funcionalidades

- Menu interativo no terminal em português.
- Consulta de filmes pelo título.
- Exibição do título, ano, nota no IMDb e data de lançamento do filme.
- Consulta de séries pelo título.
- Exibição do título, total de temporadas e nota no IMDb da série.
- Consulta de temporadas e episódios na OMDb.
- Repetição do menu para realizar várias pesquisas sem reiniciar a aplicação.
- Validação da entrada do menu para garantir que seja informado um número inteiro.
- Mapeamento dos campos JSON externos para records Java imutáveis.

### Como funciona

A aplicação segue este fluxo:

1. O Spring Boot inicia `MovieCatalogApiApplication`.
2. A aplicação chama `Menu.mainMenu()` por meio de `CommandLineRunner`.
3. O usuário escolhe uma das três opções:
   - `1`: pesquisar um filme;
   - `2`: pesquisar uma série;
   - `3`: encerrar.
4. O título informado é normalizado, substituindo espaços por `+`.
5. `ApiReader` cria uma requisição HTTP com o `HttpClient` do Java e a envia para a OMDb.
6. `ConvertData` usa o `ObjectMapper` do Jackson para desserializar a resposta JSON no record adequado.
7. Os dados mapeados são exibidos no terminal.
8. Após a consulta, o menu é apresentado novamente até que o usuário encerre o programa.

Na pesquisa de uma série, a aplicação primeiro obtém seus dados gerais e a quantidade total de temporadas. Em seguida, consulta todas as temporadas na OMDb e mantém os objetos `SeasonDTO` resultantes em memória. Depois disso, solicita ao usuário a temporada desejada.

### Modelos de dados

| Record | Dados da OMDb representados |
| --- | --- |
| `MovieDTO` | Título, ano, nota no IMDb e data de lançamento |
| `SeriesDTO` | Título, total de temporadas e nota no IMDb |
| `SeasonDTO` | Número da temporada e lista de episódios |
| `EpisodeDTO` | Título, número do episódio, nota no IMDb e data de lançamento |

Os records usam `@JsonAlias` para mapear os nomes dos campos da OMDb e `@JsonIgnoreProperties(ignoreUnknown = true)` para que campos não utilizados pela aplicação não impeçam a desserialização.

### Tecnologias

- Java 21
- Spring Boot 4.1.0
- Maven / Maven Wrapper
- Jackson Databind 2.22.1
- Java HTTP Client
- JUnit 5 e Spring Boot Test
- API OMDb

### Pré-requisitos

Antes de executar o projeto, verifique se você possui:

- Java Development Kit (JDK) 21 instalado;
- acesso à internet, pois os dados do catálogo são consultados durante a execução;
- uma [chave válida da API OMDb](https://www.omdbapi.com/apikey.aspx).

Não é necessário instalar o Maven globalmente, pois o repositório inclui o Maven Wrapper.

### Configuração

O repositório contém um arquivo `.env` com as entradas `OMDB_API_URL` e `OMDB_API_KEY`. Entretanto, o código atual **não** carrega essas variáveis de ambiente: a URL e a chave da OMDb estão declaradas diretamente em `Menu.java`.

Para usar uma chave local, substitua a chave utilizada pela constante `API_KEY` em:

```text
src/main/java/br/com/moviecatalog/movie_catalog_api/main/Menu.java
```

O valor enviado à OMDb deve seguir este formato:

```text
&apikey=SUA_CHAVE_DA_API
```

Não envie uma chave real ao controle de versão. Uma melhoria recomendada é ler a chave de uma variável de ambiente ou da configuração do Spring e manter o arquivo `.env` ignorado.

### Executando a aplicação

No Linux ou macOS:

```bash
./mvnw spring-boot:run
```

No Windows:

```bat
mvnw.cmd spring-boot:run
```

Também é possível gerar e executar o arquivo JAR:

```bash
./mvnw clean package
java -jar target/movie-catalog-api-0.0.1-SNAPSHOT.jar
```

### Utilizando o menu

Após a inicialização, informe o número correspondente à operação desejada:

```text
1 - Consultar filmes
2 - Consultar séries
3 - Encerrar
```

Para um filme, digite o título e a aplicação exibirá suas informações básicas de catálogo.

Para uma série, digite o título. A aplicação apresenta os dados da série, consulta todas as temporadas disponíveis e pergunta qual temporada deve ser exibida. As pesquisas são feitas pelo título e dependem do resultado selecionado pela OMDb para o parâmetro `t`.

### Executando os testes

No Linux ou macOS:

```bash
./mvnw test
```

No Windows:

```bat
mvnw.cmd test
```

A suíte atual contém um teste de carregamento do contexto do Spring.

### Estrutura do projeto

```text
src
├── main
│   ├── java/br/com/moviecatalog/movie_catalog_api
│   │   ├── MovieCatalogApiApplication.java  # Ponto de entrada
│   │   ├── dto                              # Records das respostas da OMDb
│   │   ├── main/Menu.java                   # Fluxo e menu do terminal
│   │   ├── service
│   │   │   ├── ApiReader.java               # Requisições HTTP
│   │   │   └── ConvertData.java             # Desserialização do JSON
│   │   └── utils/InputReader.java           # Leitura das entradas do terminal
│   └── resources/application.properties     # Nome da aplicação no Spring
└── test
    └── java/.../MovieCatalogApiApplicationTests.java
```

### Tratamento de erros e limitações atuais

- Não existem endpoints REST.
- Os resultados não são persistidos; todos os dados existem apenas durante a execução atual.
- A aplicação depende do serviço OMDb e de uma conexão ativa com a internet.
- Respostas de erro da OMDb, status HTTP sem sucesso, timeouts e títulos inválidos ou inexistentes não são tratados explicitamente.
- Falhas de HTTP ou processamento JSON são convertidas em `RuntimeException`s não verificadas.
- A chave da API está atualmente incorporada ao código, em vez de ser lida de uma configuração externa.
- Na busca de séries, todas as temporadas são consultadas antecipadamente, o que pode gerar várias requisições HTTP sequenciais.
- O filtro atual de exibição de temporadas sempre seleciona a temporada `1`, independentemente da temporada informada pelo usuário.
- A mensagem de validação da temporada é exibida atualmente quando o valor informado é válido, em vez de inválido.
- O utilitário de entrada aceita números inteiros e decimais, embora a leitura decimal não seja usada pelo menu atual.
- A cobertura automatizada atualmente se limita a verificar se o contexto do Spring é inicializado.

### Melhorias possíveis

- Carregar a URL e a chave da OMDb por variáveis de ambiente ou propriedades do Spring.
- Corrigir a validação da temporada e exibir a temporada escolhida pelo usuário.
- Tratar de forma amigável títulos inexistentes, chaves inválidas, limites de requisições, falhas de conexão e respostas malformadas.
- Validar os códigos de status HTTP e preservar a interrupção da thread em `ApiReader`.
- Consultar somente a temporada solicitada, em vez de carregar todas antecipadamente.
- Adicionar testes unitários e de integração para as opções do menu, conversão de dados e falhas da API externa.
- Adotar injeção de dependências nos serviços para facilitar os testes.
- Adicionar cache ou persistência para títulos consultados anteriormente.
