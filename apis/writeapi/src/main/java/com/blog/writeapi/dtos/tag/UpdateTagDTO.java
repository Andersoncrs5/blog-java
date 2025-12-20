package com.blog.writeapi.dtos.tag;

import com.blog.writeapi.utils.annotations.valid.global.StringClear.StringClear;
import com.blog.writeapi.utils.annotations.valid.global.isId.IsId;
import com.blog.writeapi.utils.annotations.valid.global.slug.Slug;
import com.blog.writeapi.utils.annotations.valid.tag.existsTagById.ExistsTagById;
import com.blog.writeapi.utils.annotations.valid.tag.uniqueTagByName.UniqueTagByName;
import com.blog.writeapi.utils.annotations.valid.tag.uniqueTagBySlug.UniqueTagBySlug;
import jakarta.validation.constraints.Size;

public record UpdateTagDTO(

        @IsId
        @ExistsTagById
        Long id,

        @StringClear
        @Size(max = 70)
        @UniqueTagByName
        String name,

        @Size(max = 80)
        @Slug
        @UniqueTagBySlug
        String slug,

        @Size(max = 200)
        String description,

        Boolean isActive,
        Boolean isVisible,
        Boolean isSystem,
        java.time.OffsetDateTime lastUsedAt
) {
}
