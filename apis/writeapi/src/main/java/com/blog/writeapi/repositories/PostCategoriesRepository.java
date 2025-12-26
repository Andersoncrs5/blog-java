package com.blog.writeapi.repositories;

import com.blog.writeapi.models.PostCategoriesModel;
import com.blog.writeapi.utils.annotations.valid.global.isId.IsId;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCategoriesRepository extends JpaRepository<@NonNull PostCategoriesModel, @NonNull Long> {
    boolean existsByPostIdAndPrimaryTrue(@IsId Long value);
}
