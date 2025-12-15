package com.blog.writeapi.dtos.category;

public record UpdateCategoryDTO(
        String name,
        String description,
        String slug,
        Boolean isActive,
        Boolean visible,
        Integer displayOrder
) {
}
