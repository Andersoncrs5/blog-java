package com.blog.writeapi.utils.res.swagger.postFavorite;

import com.blog.writeapi.dtos.postFavorite.PostFavoriteDTO;
import com.blog.writeapi.utils.res.ResponseHttp;

public record ResponsePostFavoriteDTO(
        ResponseHttp<PostFavoriteDTO> responseHttp
) {
}
