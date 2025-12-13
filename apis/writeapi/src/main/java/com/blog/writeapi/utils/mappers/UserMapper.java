package com.blog.writeapi.utils.mappers;

import com.blog.writeapi.dtos.user.UpdateUserDTO;
import com.blog.writeapi.dtos.user.UserDTO;
import com.blog.writeapi.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserModel toModel(UserDTO dto);

    UserDTO toDTO(UserModel user);

    void merge(UpdateUserDTO dto, @MappingTarget UserModel target);

    default String mapOptionalString(Optional<String> optional) {
        return optional.isPresent() ? optional.orElse(null) : null;
    }

}