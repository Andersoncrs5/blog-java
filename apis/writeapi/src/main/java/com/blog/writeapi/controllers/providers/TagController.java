package com.blog.writeapi.controllers.providers;

import com.blog.writeapi.controllers.docs.TagControllerDocs;
import com.blog.writeapi.dtos.tag.CreateTagDTO;
import com.blog.writeapi.dtos.tag.TagDTO;
import com.blog.writeapi.models.TagModel;
import com.blog.writeapi.services.interfaces.ITagService;
import com.blog.writeapi.utils.mappers.TagMapper;
import com.blog.writeapi.utils.res.ResponseHttp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1/tag")
@RequiredArgsConstructor
@Validated
public class TagController implements TagControllerDocs {

    private final ITagService service;
    private final TagMapper mapper;

    @Override
    public ResponseEntity<?> create(@Valid @RequestBody CreateTagDTO dto, HttpServletRequest request) {
        TagModel tagModel = this.service.create(dto);

        TagDTO tagDTO = this.mapper.toDTO(tagModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseHttp<>(
                tagDTO,
                "Tag created with successfully",
                UUID.randomUUID().toString(),
                1,
                true,
                OffsetDateTime.now()
        ));
    }

    @Override
    public ResponseEntity<?> get(@Positive Long id, HttpServletRequest request) {
        Optional<TagModel> optional = this.service.getById(id);

        if (optional.isEmpty())
            return this.buildResponseError("Tag not found", HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseHttp<>(
                optional.get(),
                "Tag found with successfully",
                UUID.randomUUID().toString(),
                1,
                true,
                OffsetDateTime.now()
        ));
    }

    private ResponseEntity<?> buildResponseError(String message, HttpStatus status) {
        return ResponseEntity.status(status).body(new ResponseHttp<>(
                null, message, UUID.randomUUID().toString(), 0, false, OffsetDateTime.now()
        ));
    }

}
