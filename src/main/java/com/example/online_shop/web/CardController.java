package com.example.online_shop.web;

import com.example.online_shop.model.dto.CardDTO;
import com.example.online_shop.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class CardController {

    private final CardService cardService;

    @PostMapping(value = "/cards/add")
    public CardDTO addNewCard(CardDTO cardDTO){
        return cardService.addNewCard(cardDTO);
    }
}
