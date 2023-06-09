package com.example.online_shop.mappers;

import com.example.online_shop.model.Card;
import com.example.online_shop.model.dto.CardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface CardMapper {

    CardMapper MAPPER = Mappers.getMapper(CardMapper.class);

    Card map(CardDTO cardDTO);

    CardDTO map(Card card);

    default Page<CardDTO> map(Page<Card> cards){
        return cards.map(this::map);
    }
}
