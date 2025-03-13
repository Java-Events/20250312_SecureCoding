package junit.com.svenruppert.securecoding.logindemo.services.login.passwords.checks.step01;

import com.svenruppert.securecoding.logindemo.services.login.passwords.PasswordCheckResult;
import com.svenruppert.securecoding.logindemo.services.login.passwords.checks.CaseValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CaseValidatorTest {

  @ParameterizedTest
  @CsvSource({
      "helloworld , false",
      "helloWorld , true"
  })
  void test001(String input, Boolean expected) {
    CaseValidator validator = new CaseValidator();
    PasswordCheckResult checkResult = validator.isValid(input);
    assertNotNull(checkResult);
    assertEquals(expected, checkResult.ok());
  }
}