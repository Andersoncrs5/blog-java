package com.blog.writeapi.utils.mappers;

import com.blog.writeapi.dtos.postTag.CreatePostTagDTO;
import com.blog.writeapi.dtos.postTag.PostTagDTO;
import com.blog.writeapi.dtos.postTag.UpdatePostTagDTO;
import com.blog.writeapi.models.PostTagModel;
import org.mapstruct.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import jakarta.validation.constraints.NotNull;

@Mapper(componentModel = "spring")
public interface PostTagMapper {

    PostTagDTO toDTO(@NotNull PostTagModel model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "post", ignore = true)
    @Mapping(target = "tag", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    PostTagModel toModel(@NotNull CreatePostTagDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "post", ignore = true)
    @Mapping(target = "tag", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    void merge(UpdatePostTagDTO dto, @MappingTarget PostTagModel model);

    default OffsetDateTime map(LocalDateTime dateTime) {
        return dateTime == null ? null : dateTime.atOffset(ZoneOffset.UTC);
    }

    default LocalDateTime map(OffsetDateTime offsetDateTime) {
        return offsetDateTime == null ? null : offsetDateTime.toLocalDateTime();
    }
}
