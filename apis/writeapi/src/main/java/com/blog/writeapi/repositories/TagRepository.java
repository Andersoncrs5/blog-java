package com.blog.writeapi.repositories;

import com.blog.writeapi.models.TagModel;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<@NonNull TagModel, @NonNull Long> {
    Optional<TagModel> findByName(String name);
    Boolean existsByName(String name);

    Optional<TagModel> findBySlug(String slug);
    Boolean existsBySlug(String slug);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT t FROM TagModel t WHERE t.id = :id")
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "4000")})
    Optional<TagModel> findByIdForUpdate(@Param("id") Long id);
}
