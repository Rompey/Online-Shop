package com.example.online_shop.service;

import com.example.online_shop.model.Card;
import com.example.online_shop.model.dto.CardDTO;
import com.example.online_shop.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public CardDTO addNewCard(CardDTO cardDTO) {
        Card card = cardRepository.save(buildCard(cardDTO));
        return new CardDTO(card.getName(),
                card.getCardNumber(),
                card.getCardValidity(),
                card.getCvv(),
                card.isExpired());
    }

    private Card buildCard(CardDTO cardDTO) {
        return Card.builder()
                .name(cardDTO.name())
                .cardNumber(cardDTO.cardNumber())
                .cardValidity(cardDTO.cardValidity())
                .cvv(cardDTO.cvv())
                .isExpired(cardDTO.isExpired())
                .build();
    }
}
