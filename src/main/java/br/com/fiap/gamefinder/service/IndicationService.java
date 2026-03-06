package br.com.fiap.gamefinder.service;

import br.com.fiap.gamefinder.dto.GameDataResponse;
import br.com.fiap.gamefinder.dto.IndicationResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IndicationService {

    public IndicationResponse getIndication(GameDataResponse gameData, String gameTitle) {

        var games = List.of("grand-theft-auto-v", "the-witcher-3-wild-hunt", "prey");
        var response = games.contains(gameTitle) ? isGoodGame(gameData) : "Jogo não computado";
        return new IndicationResponse(response);
    }

    private String isGoodGame(GameDataResponse gameData) {

        var rating = gameData.rating();
        var totalRatings = gameData.ratingsCount();
        var releaseDate = LocalDate.parse(gameData.released());
        var isNew = releaseDate
                .plusYears(10)
                .isAfter(LocalDate.now());

        System.out.println(gameData);

        if (rating >= 4 & totalRatings >= 1000 & isNew) {
            return "Altamente Recomendado !";
        } else if (rating >= 3.5 & totalRatings >= 500) {
            return "Vale a pena";
        } else {
            return "Melhor ver um filme...";
        }
    }

}
