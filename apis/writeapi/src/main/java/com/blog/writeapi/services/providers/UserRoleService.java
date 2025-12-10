package com.blog.writeapi.services.providers;

import cn.hutool.core.lang.Snowflake;
import com.blog.writeapi.models.RoleModel;
import com.blog.writeapi.models.UserModel;
import com.blog.writeapi.models.UserRoleModel;
import com.blog.writeapi.repositories.UserRoleRepository;
import com.blog.writeapi.services.interfaces.IUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleService implements IUserRoleService {

    private final UserRoleRepository repository;
    private final Snowflake generator;

    @Override
    public UserRoleModel create(UserModel user, RoleModel role) {
        UserRoleModel userRoleModel = new UserRoleModel();

        user.setId(generator.nextId());
        userRoleModel.setUser(user);
        userRoleModel.setRole(role);

        return this.repository.save(userRoleModel);
    }

    @Override
    public Optional<UserRoleModel> getById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.repository.existsById(id);
    }

    @Override
    public Optional<UserRoleModel> getByUserAndRole(UserModel user, RoleModel role) {
        return this.repository.findByUserAndRole(user, role);
    }

    @Override
    public Boolean existsByUserAndRole(UserModel user, RoleModel role) {
        return this.repository.existsByUserAndRole(user, role);
    }

    @Override
    public void delete(UserRoleModel user){
        this.repository.delete(user);
    }

    @Override
    public List<UserRoleModel> getAllByUser(UserModel user){
        return this.repository.findAllByUser(user);
    }
}
