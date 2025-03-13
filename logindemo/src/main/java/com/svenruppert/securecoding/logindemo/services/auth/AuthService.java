package com.svenruppert.securecoding.logindemo.services.auth;


import com.svenruppert.securecoding.logindemo.services.login.LoginResult;
import com.svenruppert.securecoding.logindemo.services.login.LoginService;
import com.svenruppert.securecoding.logindemo.services.user.User;
import com.svenruppert.securecoding.logindemo.services.user.UserService;

public class AuthService {
  private final LoginService loginService;
  private final UserService userService;

  public AuthService(LoginService loginService,
                     UserService userService) {
    this.loginService = loginService;
    this.userService = userService;
  }

  public User authenticateUser(String username, String password) {
    LoginResult authenticate = loginService.authenticate(username, password);
    return userService.userByLogin(authenticate.login());
  }

}
