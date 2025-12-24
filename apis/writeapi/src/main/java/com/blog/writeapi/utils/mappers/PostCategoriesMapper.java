package com.blog.writeapi.utils.mappers;

import com.blog.writeapi.dtos.postCategories.CreatePostCategoriesDTO;
import com.blog.writeapi.dtos.postCategories.PostCategoriesDTO;
import com.blog.writeapi.dtos.postCategories.UpdatePostCategoriesDTO;
import com.blog.writeapi.models.PostCategoriesModel;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface PostCategoriesMapper {

    PostCategoriesModel toModel(PostCategoriesDTO dto);
    PostCategoriesDTO toDTO(PostCategoriesModel category);

    @Mapping(target = "post", ignore = true)
    @Mapping(target = "category", ignore = true)
    PostCategoriesModel toModel(CreatePostCategoriesDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void merge(UpdatePostCategoriesDTO dto, @MappingTarget PostCategoriesModel category);

    default OffsetDateTime map(LocalDateTime dateTime) {
        return dateTime == null ? null : dateTime.atOffset(ZoneOffset.UTC);
    }

    default LocalDateTime map(OffsetDateTime offsetDateTime) {
        return offsetDateTime == null ? null : offsetDateTime.toLocalDateTime();
    }

}
