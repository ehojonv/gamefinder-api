package br.com.fiap.gamefinder.service;

import br.com.fiap.gamefinder.dto.GameDataResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class GameDataService {

    private final RestClient restClient;

    public GameDataService() {
        this.restClient = RestClient.create("https://api.rawg.io/api");
    }

    public GameDataResponse getGameData(String gameName) {

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/games/" + gameName)
                        .queryParam("key", "57355685daee4ceeae36ba76e135114c")
                        .build())
                .retrieve()
                .body(GameDataResponse.class);
    }
}