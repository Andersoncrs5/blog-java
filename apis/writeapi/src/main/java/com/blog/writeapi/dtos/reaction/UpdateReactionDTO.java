package com.blog.writeapi.dtos.reaction;

import com.blog.writeapi.models.enums.reaction.ReactionTypeEnum;

public record UpdateReactionDTO(
        String name,
        String emojiUrl,
        String emojiUnicode,
        Long displayOrder,
        Boolean active,
        Boolean visible,
        ReactionTypeEnum type
) {
}
