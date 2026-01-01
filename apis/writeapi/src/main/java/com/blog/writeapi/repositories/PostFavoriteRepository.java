package com.blog.writeapi.repositories;

import com.blog.writeapi.models.PostFavoriteModel;
import com.blog.writeapi.models.PostModel;
import com.blog.writeapi.models.UserModel;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostFavoriteRepository extends JpaRepository<@NonNull PostFavoriteModel, @NonNull Long > {
    Boolean existsByPostAndUser(PostModel post, UserModel user);

    Optional<PostFavoriteModel> findByPostAndUser(PostModel post, UserModel user);

}
