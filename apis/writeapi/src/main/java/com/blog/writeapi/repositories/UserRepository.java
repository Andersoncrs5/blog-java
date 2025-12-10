package com.blog.writeapi.repositories;

import com.blog.writeapi.models.UserModel;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<@NonNull UserModel, @NonNull Long> {
    UserDetails findByEmail(String email);
}