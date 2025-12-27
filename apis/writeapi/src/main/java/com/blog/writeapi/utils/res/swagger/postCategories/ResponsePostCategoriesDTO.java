package com.blog.writeapi.utils.res.swagger.postCategories;

import com.blog.writeapi.dtos.postCategories.PostCategoriesDTO;
import com.blog.writeapi.utils.res.ResponseHttp;

public record ResponsePostCategoriesDTO(
        ResponseHttp<PostCategoriesDTO> dto
) {
}
