package junit.com.svenruppert.securecoding.logindemo.services.login.passwords.checks.step03.tests;


import com.svenruppert.securecoding.logindemo.services.login.passwords.checks.*;
import junit.com.svenruppert.securecoding.logindemo.services.login.passwords.checks.step03.AbstractPasswordValidatorTest;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class CommonPasswordValidatorTest
    extends AbstractPasswordValidatorTest {
  @Override
  protected Stream<InputPair> generateInputPairs() {
    return Stream.of(
        new InputPair("password", false),
        new InputPair("12345678", false),
        new InputPair("Hsjhhftr%&", true)
    );
  }

  @Override
  protected Supplier<PasswordValidator> validatorSupplier() {
    return CommonPasswordValidator::new;
  }
}
