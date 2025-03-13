package com.svenruppert.securecoding.logindemo.services.login.passwords;

import com.svenruppert.dependencies.core.logger.HasLogger;
import com.svenruppert.securecoding.logindemo.services.login.passwords.checks.*;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class PasswordValidatorService implements HasLogger {

  private Stream<Supplier<PasswordValidator>> ruleChecks() {
    return Stream.of(
        CaseValidator::new,
        CommonPasswordValidator::new,
        DigitValidator::new,
        ()-> new LengthValidator(8, 16),
//      ()-> new PersonalInfoValidator("",""), //TODO
//      ()-> new ReusedPasswordValidator(previousPasswords),
        SpecialCharacterValidator::new,
        WhitespaceValidator::new
    );
  }

  public List<PasswordCheckResult> checkRules(String password) {
    return ruleChecks()
        .map(Supplier::get)
        .map(rule -> rule.isValid(password))
        .peek(result -> logger().info("Password check result: {}", result))
        .toList();
  }


}
