package com.blog.writeapi.repositories;

import com.blog.writeapi.models.UserRoleModel;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<@NonNull UserRoleModel, @NonNull Long> {
}
