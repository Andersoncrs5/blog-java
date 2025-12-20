package com.blog.writeapi.models.enums.Post;

public enum PostStatusEnum {
    DRAFT,
    SCHEDULED,
    PUBLISHED,
    ARCHIVED,
    BANNED,
    DELETED;
    public boolean isPubliclyVisible() {
        return this == PUBLISHED;
    }
}