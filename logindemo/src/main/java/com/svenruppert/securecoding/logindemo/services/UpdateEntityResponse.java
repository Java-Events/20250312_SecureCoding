package com.svenruppert.securecoding.logindemo.services;

public record UpdateEntityResponse<T> (boolean updated, String message, T value) {
}
