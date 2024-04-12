package dev.tuxbe.aemiage.model;

public record ResponseApi(boolean hasError, String message, Object data) {
}
