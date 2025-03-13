package com.svenruppert.securecoding.logindemo.services;

public record CreateEntityResponse<T>(boolean created, String message, T entity) {
}
