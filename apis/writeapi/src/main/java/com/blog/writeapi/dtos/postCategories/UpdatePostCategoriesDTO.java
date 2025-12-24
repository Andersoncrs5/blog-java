package com.blog.writeapi.dtos.postCategories;

public record UpdatePostCategoriesDTO(
        Integer displayOrder,
        boolean primary,
        boolean active
) {
}
