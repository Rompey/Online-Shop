package com.example.online_shop.mappers;

import com.example.online_shop.model.User;
import com.example.online_shop.model.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    User map(UserDTO userDTO);

    UserDTO map(User user);

    default Page<UserDTO> map(Page<User> users){
        return users.map(this::map);
    }

}
