package com.blog.writeapi.services.interfaces;

import com.blog.writeapi.models.RoleModel;
import com.blog.writeapi.models.UserModel;
import com.blog.writeapi.models.UserRoleModel;

import java.util.List;
import java.util.Optional;

public interface IUserRoleService {
    UserRoleModel create(UserModel user, RoleModel role);
    List<UserRoleModel> getAllByUser(UserModel user);
    void delete(UserRoleModel user);
    Optional<UserRoleModel> getById(Long id);
    Boolean existsById(Long id);
    Optional<UserRoleModel> getByUserAndRole(UserModel user, RoleModel role);
    Boolean existsByUserAndRole(UserModel user, RoleModel role);
}
