package com.svenruppert.securecoding.logindemo.services;

public record DeleteEntityResponse<T>(boolean deleted, String message, T value){
}
