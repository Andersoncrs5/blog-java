package com.blog.writeapi.repositories;

import com.blog.writeapi.models.CategoryModel;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<@NonNull CategoryModel, @NonNull Long> {
    Boolean existsByName(String name);
    Optional<CategoryModel> findByName(String name);
    Optional<CategoryModel> findBySlug(String slug);
    Boolean existsBySlug(String slug);
}
