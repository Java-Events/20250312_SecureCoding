package com.svenruppert.securecoding.logindemo.services.login;

public record Login(String loginName, String passwordHash, String salt, int uid) {
  public Login() {
    this("anonymous", "anonymous", "anonymous", -1);
  }
}
