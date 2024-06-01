package com.mjc.school.service.validate;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    private static final int MIN_AUTHOR_NAME_LENGTH = 3;
    private static final int MAX_AUTHOR_NAME_LENGTH = 15;
    private static final int MIN_TAG_NAME_LENGTH = 3;
    private static final int MAX_TAG_NAME_LENGTH = 15;
    private static final int MIN_COMMENT_CONTENT_LENGTH = 5;
    private static final int MAX_COMMENT_CONTENT_LENGTH = 255;
    private static final int MIN_NEWS_CONTENT_LENGTH = 5;
    private static final int MAX_NEWS_CONTENT_LENGTH = 255;
    private static final int MAX_NEWS_TITLE_LENGTH = 30;
    private static final int MIN_NEWS_TITLE_LENGTH = 5;

    public NewsDtoRequest validateNews(NewsDtoRequest newsDtoRequest) {
        if (!validateField(newsDtoRequest.authorName(), MIN_AUTHOR_NAME_LENGTH, MAX_AUTHOR_NAME_LENGTH)) {
            throw new ValidationException(String.format("Author name should be between %1$d and %2$d characters ", MIN_AUTHOR_NAME_LENGTH, MAX_AUTHOR_NAME_LENGTH));
        }
        if (!validateField(newsDtoRequest.content(), MIN_NEWS_CONTENT_LENGTH, MAX_NEWS_CONTENT_LENGTH)) {
            throw new ValidationException(String.format("News content should be between %1$d and %2$d characters. ", MIN_NEWS_CONTENT_LENGTH, MAX_NEWS_CONTENT_LENGTH));
        }
        if (!validateField(newsDtoRequest.title(), MIN_NEWS_TITLE_LENGTH, MAX_NEWS_TITLE_LENGTH)) {
            throw new ValidationException(String.format("News title should be between %1$d and %2$d characters. ", MIN_NEWS_TITLE_LENGTH, MAX_NEWS_TITLE_LENGTH));
        }
        newsDtoRequest.tagNames().stream().map(tagName -> validateField(tagName, MIN_TAG_NAME_LENGTH, MAX_TAG_NAME_LENGTH)).forEach(t -> {
        });
        return newsDtoRequest;
    }

    public TagDtoRequest validateTag(TagDtoRequest tagDtoRequest) {
        if (!validateField(tagDtoRequest.name(), MIN_TAG_NAME_LENGTH, MAX_TAG_NAME_LENGTH)) {
            throw new ValidationException(String.format("Tag name should be between %1$d and %2$d characters.", MIN_TAG_NAME_LENGTH, MAX_TAG_NAME_LENGTH));
        }
        return tagDtoRequest;
    }

    public CommentDtoRequest validateComment(CommentDtoRequest commentDtoRequest) {
        if (!validateField(commentDtoRequest.content(), MIN_COMMENT_CONTENT_LENGTH, MAX_COMMENT_CONTENT_LENGTH)) {
            throw new ValidationException(String.format("Comment content should be between %1$d and %2$d characters. ", MIN_COMMENT_CONTENT_LENGTH, MAX_COMMENT_CONTENT_LENGTH));
        }
        return commentDtoRequest;
    }

    public AuthorDtoRequest validateAuthor(AuthorDtoRequest authorDtoRequest) {
        if (!validateField(authorDtoRequest.name(), MIN_AUTHOR_NAME_LENGTH, MAX_AUTHOR_NAME_LENGTH)) {
            throw new ValidationException(String.format("Author name should be between %1$d and %2$d characters. ", MIN_AUTHOR_NAME_LENGTH, MAX_AUTHOR_NAME_LENGTH));
        }
        return authorDtoRequest;
    }

    private boolean validateField(String stringToValidate, int minLength, int maxLength) {
        if (stringToValidate.length() > maxLength || stringToValidate.length() < minLength) {
            return false;
        }
        return true;
    }
}
