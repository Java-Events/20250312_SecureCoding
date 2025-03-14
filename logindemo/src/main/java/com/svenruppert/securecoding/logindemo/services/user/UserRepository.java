package com.svenruppert.securecoding.logindemo.services.user;

import com.svenruppert.dependencies.core.logger.HasLogger;
import com.svenruppert.securecoding.logindemo.services.CreateEntityResponse;
import com.svenruppert.securecoding.logindemo.services.DeleteEntityResponse;

import java.util.Map;

import static java.util.HashMap.newHashMap;

public class UserRepository
    implements HasLogger {

  public final static User ANONYMOUS_USER = new User();
  private static final Map<Integer, User> userRepo = newHashMap(100);

  public void clearRepo(){
    userRepo.clear();
  }


  public User userByUID(int uid) {
    return userRepo.getOrDefault(uid, ANONYMOUS_USER);
  }

  public DeleteEntityResponse<User> deleteUser(User user) {
    userRepo.remove(user.uid());
    return new DeleteEntityResponse<>(true, String.format("User %s deleted", user.uid()), user);
  }


  public CreateEntityResponse<User> createUser(int uid, String forename, String surname) {
    User value = new User(uid, forename, surname);
    userRepo.put(uid, value);
    return new CreateEntityResponse<>(true, "User " + uid + " created", value);
  }
}
