package com.orioinc.storageservice.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundKeyException extends ResponseStatusException {
    public NotFoundKeyException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
