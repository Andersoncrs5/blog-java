package com.blog.writeapi.utils.annotations.valid.PostCategories.uniquePrimairyInPost;

import com.blog.writeapi.dtos.postCategories.CreatePostCategoriesDTO;
import com.blog.writeapi.services.interfaces.IPostCategoriesService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniquePrimaryCategoryInPostValidator
        implements ConstraintValidator<UniquePrimaryCategoryInPost, CreatePostCategoriesDTO> {

    private final IPostCategoriesService service;

    @Override
    public boolean isValid(CreatePostCategoriesDTO dto, ConstraintValidatorContext context) {
        if (!dto.primary()) {
            return true;
        }

        boolean alreadyHasPrimary = service.existsByPostIdAndPrimaryTrue(dto.postId());

        return !alreadyHasPrimary;
    }
}