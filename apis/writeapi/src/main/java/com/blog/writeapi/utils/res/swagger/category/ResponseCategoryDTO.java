package com.blog.writeapi.utils.res.swagger.category;

import com.blog.writeapi.dtos.category.CategoryDTO;
import com.blog.writeapi.utils.res.ResponseHttp;

public record ResponseCategoryDTO(
        ResponseHttp<CategoryDTO> res
) {
}
