package com.blog.writeapi.configs.security.executors.postCategories;

import com.blog.writeapi.services.interfaces.IPostCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("postCategorySecurity")
public class PostCategorySecurityExecutor {

    @Autowired
    private IPostCategoriesService service;

    public boolean isAuthor(Long id, String email) {
        if (id == null || email == null) return false;

        return service.getById(id)
                .map(pc -> pc.getPost().getAuthor().getEmail().equals(email))
                .orElse(false);
    }

}
