package com.example.demo.util;

import com.example.demo.response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URI;

public class ResponseFactory {

    public static <T> ResponseEntity<RestResponse<T>> ok(T result, String message) {
        RestResponse<T> response = new RestResponse<>();
        response.setBody(result);
        response.setCode(HttpStatus.OK.value());
        response.setMessage(message);
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<RestResponse<T>> ok(T result) {
        return ok(result, "");
    }

    public static <T> ResponseEntity<RestResponse<T>> created(T result, String message, String uri) {
        RestResponse<T> response = new RestResponse<>();
        response.setBody(result);
        response.setCode(HttpStatus.CREATED.value());
        response.setMessage(message);
        return ResponseEntity.created(URI.create(uri)).body(response);
    }

    public static <T> ResponseEntity<RestResponse<T>> noContent(String message) {
        RestResponse<T> response = new RestResponse<>();
        response.setCode(HttpStatus.NO_CONTENT.value());
        response.setMessage(message);
        return ResponseEntity.noContent().build();
    }

    public static <T> ResponseEntity<RestResponse<T>> notFound(String message) {
        RestResponse<T> response = new RestResponse<>();
        response.setCode(HttpStatus.NOT_FOUND.value());
        response.setHasError(true);
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(response);
    }

    public static <T> ResponseEntity<RestResponse<T>> accepted(String message) {
        RestResponse<T> response = new RestResponse<>();
        response.setCode(HttpStatus.ACCEPTED.value());
        response.setMessage(message);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    public static <T> ResponseEntity<RestResponse<T>> badRequest(String message) {
        RestResponse<T> response = new RestResponse<>();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(message);
        response.setHasError(true);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    public static <T> ResponseEntity<RestResponse<T>> internalServerError(String message) {
        RestResponse<T> response = new RestResponse<>();
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage(message);
        response.setHasError(true);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    public static <T> ResponseEntity<RestResponse<T>> conflict(String message) {
        RestResponse<T> response = new RestResponse<>();
        response.setCode(HttpStatus.CONFLICT.value());
        response.setMessage(message);
        response.setHasError(true);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    //todo standardize exceptions
    public static ResponseEntity<?> handle(HttpClientErrorException e) {
        switch (e.getStatusCode().value()) {
            case 204:
                return noContent(e.getMessage());
            case 400:
                return badRequest(e.getMessage());
            case 404:
                return notFound(e.getMessage());
            case 409:
                return conflict(e.getMessage());
            default:
                return internalServerError(e.getMessage());
        }
    }
}
