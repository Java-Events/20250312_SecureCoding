package junit.com.svenruppert.securecoding.logindemo.services.user;

import com.svenruppert.securecoding.logindemo.services.CreateEntityResponse;
import com.svenruppert.securecoding.logindemo.services.DeleteEntityResponse;
import com.svenruppert.securecoding.logindemo.services.UpdateEntityResponse;
import com.svenruppert.securecoding.logindemo.services.login.Login;
import com.svenruppert.securecoding.logindemo.services.login.LoginService;
import com.svenruppert.securecoding.logindemo.services.user.User;
import com.svenruppert.securecoding.logindemo.services.user.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.svenruppert.securecoding.logindemo.services.user.UserRepository.ANONYMOUS_USER;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

  private static void checkMaxMustermann(User user) {
    assertNotNull(user);
    assertNotEquals(-1, user.uid());
    assertEquals("Max", user.forename());
    assertEquals("Mustermann", user.surname());
  }
  @AfterEach
  void tearDown() {
    ServicesSingletonsParameterResolver.cleaRepos();
  }

  @Test
  void test001(
      @ServicesSingletonsParameterResolver.SingletonService UserService service,
      @ServicesSingletonsParameterResolver.SingletonService LoginService loginService) {

    CreateEntityResponse<User> createResponse = service.createUser(
        "max.mustermann",
        "SecureMe",
        "Max",
        "Mustermann");

    assertNotNull(createResponse);
    User user = createResponse.entity();

    checkMaxMustermann(user);
    checkMaxMustermann(service.userByUserName("max.mustermann"));
    checkMaxMustermann(service.userByUID(user.uid()));

    UpdateEntityResponse<Login> loginUpdate = loginService.changePassword(
        user.uid(),
        "SecureMe",
        "password");
    assertNotNull(loginUpdate);
    assertTrue(loginUpdate.updated());

    DeleteEntityResponse<User> deleteResult = service.deleteUser(user);
    assertNotNull(deleteResult);
    assertTrue(deleteResult.deleted());

    User deletedUser = service.userByUserName("max.mustermann");
    assertNotNull(deletedUser);
    assertEquals(-1, deletedUser.uid());
    assertEquals(ANONYMOUS_USER, deletedUser);

  }


}
