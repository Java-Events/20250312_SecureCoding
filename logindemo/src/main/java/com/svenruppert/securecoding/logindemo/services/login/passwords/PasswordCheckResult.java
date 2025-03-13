package com.svenruppert.securecoding.logindemo.services.login.passwords;


import com.svenruppert.securecoding.logindemo.services.login.passwords.checks.PasswordValidator;

public record PasswordCheckResult(boolean ok, String message, Class<? extends PasswordValidator> ruleName) {

}
