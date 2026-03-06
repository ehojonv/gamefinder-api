# GameFinder API

## Visão Geral
GameFinder API é um serviço web Spring Boot projetado para fornecer recomendações de videogames. Ele se integra com a [API do Banco de Dados de Videogames RAWG](https://rawg.io/apidocs) para buscar métricas de jogos como avaliações e datas de lançamento, e então aplica uma lógica personalizada para gerar uma recomendação simples.

## Como Funciona
O serviço opera através de um único endpoint de API:

1. Uma requisição `GET` é enviada para `/api/game` com um título de jogo (slug) como parâmetro de consulta.
2. O `GameDataService` chama a API externa RAWG para recuperar dados do jogo especificado, incluindo sua avaliação, o número total de avaliações e sua data de lançamento.
3. O `IndicationService` avalia esses dados em relação a um conjunto de regras predefinidas para determinar se o jogo é recomendado.
4. Um objeto JSON contendo o texto da recomendação é retornado como resposta.

**Nota:** Este serviço está atualmente configurado para fornecer recomendações para uma lista específica de jogos: `grand-theft-auto-v`, `the-witcher-3-wild-hunt` e `prey`. Qualquer outro título de jogo resultará em uma resposta `"Jogo não computado"`.

## Endpoint da API

### Obter Indicação de Jogo
Recupera uma recomendação para um jogo específico com base em seu slug de título.

- **URL:** `/api/game`
- **Método:** `GET`
- **Parâmetros de Consulta:**
  - `title` (obrigatório): O slug amigável para URL do jogo (ex.: `the-witcher-3-wild-hunt`).
- **Exemplo de Requisição:**
  ```bash
  curl -X GET "http://localhost:8080/api/game?title=prey"
  ```
- **Exemplo de Resposta de Sucesso:**
  ```json
  {
      "response": "Altamente Recomendado !"
  }
  ```
- **Possíveis Respostas de Recomendação:**
  - `"Altamente Recomendado !"`
  - `"Vale a pena"`
  - `"Melhor ver um filme..."`
  - `"Jogo não computado"` (se o jogo não estiver na lista reconhecida)

## Primeiros Passos
Siga estas instruções para obter uma cópia do projeto funcionando em sua máquina local.

### Pré-requisitos
- Java 17 ou superior
- Gradle

### Instalação e Execução
1. **Clone o repositório:**
   ```sh
   git clone https://github.com/ehojonv/gamefinder-api.git
   ```
2. **Navegue até o diretório do projeto:**
   ```sh
   cd gamefinder-api
   ```
3. **Execute a aplicação usando o wrapper do Gradle:**
   - No macOS/Linux:
     ```sh
     ./gradlew bootRun
     ```
   - No Windows:
     ```sh
     gradlew.bat bootRun
     ```

A aplicação será iniciada e estará acessível em `http://localhost:8080`.

## Configuração
- **CORS:** A configuração atual de Cross-Origin Resource Sharing (CORS) está totalmente aberta, permitindo requisições de todas as origens, métodos e cabeçalhos. Isso é configurado em `src/main/java/br/com/fiap/gamefinder/config/CorsConfig.java`.
- **Chave da API RAWG:** A chave da API para o serviço RAWG está codificada diretamente na classe `GameDataService.java`. Para ambientes de produção, é fortemente recomendado mover esta chave para um local mais seguro, como `application.yaml` ou como uma variável de ambiente.
