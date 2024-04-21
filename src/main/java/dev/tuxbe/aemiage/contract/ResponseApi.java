package dev.tuxbe.aemiage.contract;

public record ResponseApi(boolean hasError, String message, Object data) {
}
