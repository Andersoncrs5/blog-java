package com.blog.writeapi.dtos.postCategories;

public record UpdatePostCategoriesDTO(
        Integer displayOrder,
        Boolean primary,
        Boolean active
) {
}
