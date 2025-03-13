package junit.com.svenruppert.securecoding.logindemo.services.login.passwords.checks.step03.tests;


import com.svenruppert.securecoding.logindemo.services.login.passwords.checks.*;
import junit.com.svenruppert.securecoding.logindemo.services.login.passwords.checks.step03.AbstractPasswordValidatorTest;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class DigitValidatorTest
    extends AbstractPasswordValidatorTest {

  @Override
  protected Stream<InputPair> generateInputPairs() {
    return Stream.of(
        new InputPair("hello3World", true),
        new InputPair("helloWorld", false)
    );
  }

  @Override
  protected Supplier<PasswordValidator> validatorSupplier() {
    return DigitValidator::new;
  }
}
