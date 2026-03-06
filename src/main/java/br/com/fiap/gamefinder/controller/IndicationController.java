package br.com.fiap.gamefinder.controller;

import br.com.fiap.gamefinder.dto.IndicationResponse;
import br.com.fiap.gamefinder.service.GameDataService;
import br.com.fiap.gamefinder.service.IndicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/game")
public class IndicationController {

    private final IndicationService service;
    private final GameDataService gameService;

    public IndicationController(IndicationService service, GameDataService gameService) {
        this.service = service;
        this.gameService = gameService;
    }

    @GetMapping
    public IndicationResponse getIndication(@RequestParam String title) {
        return service.getIndication(gameService.getGameData(title), title);
    }

}
