package com.blog.writeapi.utils.res;

import java.time.OffsetDateTime;

public record ResponseHttp<T>(
        T data,
        String message,
        String traceId,
        int code,
        Integer version,
        boolean status,
        OffsetDateTime timestamp
) { }

