package junit.com.svenruppert.securecoding.logindemo.services.login.passwords.checks.step01;

import com.svenruppert.securecoding.logindemo.services.login.passwords.PasswordCheckResult;
import com.svenruppert.securecoding.logindemo.services.login.passwords.checks.CommonPasswordValidator;
import com.svenruppert.securecoding.logindemo.services.login.passwords.checks.PasswordValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CommonPasswordValidatorTest {


  @ParameterizedTest
  @CsvSource({
      "password , false",
      "12345678 , false",
      "Hz&tGfR , true"
  })
  void test001(String input, Boolean expected) {
    PasswordValidator validator = new CommonPasswordValidator();
    PasswordCheckResult checkResult = validator.isValid(input);
    assertNotNull(checkResult);
    assertEquals(expected, checkResult.ok());
  }
}