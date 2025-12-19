package com.blog.writeapi.dtos.tag;

import com.blog.writeapi.utils.annotations.valid.global.StringClear.StringClear;
import com.blog.writeapi.utils.annotations.valid.global.slug.Slug;
import com.blog.writeapi.utils.annotations.valid.tag.existsTagByName.ExistsTagByName;
import com.blog.writeapi.utils.annotations.valid.tag.existsTagBySlug.ExistsTagBySlug;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateTagDTO(

        @NotBlank
        @StringClear
        @Size(min = 2, max = 70)
        @ExistsTagByName
        String name,

        @Slug
        @NotBlank
        @ExistsTagBySlug
        @Size(min = 2, max = 80)
        String slug,

        @Size(max = 200)
        String description,

        @NotNull
        Boolean isActive,

        @NotNull
        Boolean isVisible,

        @NotNull
        Boolean isSystem
) {
}
