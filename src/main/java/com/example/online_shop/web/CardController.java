package com.example.online_shop.web;

import com.example.online_shop.model.dto.CardDTO;
import com.example.online_shop.model.dto.CreateCardDTO;
import com.example.online_shop.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class CardController {

    private final CardService cardService;

    @PostMapping(value = "/cards/add")
    public ResponseEntity<CardDTO> addNewCard(@RequestBody CreateCardDTO createCardDTO) {
        CardDTO cardDTO = cardService.addNewCard(createCardDTO);
        return ResponseEntity.created(URI.create("/cards/add")).body(cardDTO);
    }

    @GetMapping(value = "/cards")
    public List<CardDTO> getCards() {
        return cardService.getCards();
    }
}
