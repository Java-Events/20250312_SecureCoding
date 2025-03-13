package com.svenruppert.securecoding.logindemo.services.login.passwords.checks;


import com.svenruppert.securecoding.logindemo.services.login.passwords.PasswordCheckResult;

public interface PasswordValidator {
  PasswordCheckResult isValid(String password);
}
