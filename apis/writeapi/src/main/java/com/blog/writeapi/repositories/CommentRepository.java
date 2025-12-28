package com.blog.writeapi.repositories;

import com.blog.writeapi.models.CommentModel;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<@NonNull CommentModel, @NonNull Long> {
}
