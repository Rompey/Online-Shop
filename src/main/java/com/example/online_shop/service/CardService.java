package com.example.online_shop.service;

import com.example.online_shop.model.Card;
import com.example.online_shop.model.dto.CardDTO;
import com.example.online_shop.model.dto.CreateCardDTO;
import com.example.online_shop.model.dto.UserDTO;
import com.example.online_shop.repository.CardRepository;
import com.example.online_shop.utils.ArgonUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final UserService userService;

    @Transactional
    public CardDTO addNewCard(CreateCardDTO cardDTO) {
        Card card = cardRepository.save(buildCard(cardDTO));
        return getCardDTO(card);
    }

    public List<CardDTO> getCards() {
        List<Card> cards = cardRepository.findAll();
        return cards.stream()
                .map(this::getCardDTO)
                .collect(Collectors.toList());
    }

    @NotNull
    private CardDTO getCardDTO(Card card) {
        return new CardDTO(card.getName(),
                card.getCardNumber(),
                card.getCvv(),
                card.getCardValidity(),
                card.getUserId().stream()
                        .map(user -> new UserDTO(user.getName(), user.getLogin()))
                        .collect(Collectors.toList()));
    }

    private Card buildCard(CreateCardDTO cardDTO) {
        return Card.builder()
                .name(cardDTO.name())
                .cardNumber(cardDTO.cardNumber())
                .cardValidity(Timestamp.from(Instant.now().plusSeconds(94608000)))
                .cvv(ArgonUtil.hashCVV(cardDTO.cvv()))
                .userId(List.of(userService.getUserByNameAndLogin(cardDTO.userId().name(), cardDTO.userId().login())))
                .build();
    }
}
