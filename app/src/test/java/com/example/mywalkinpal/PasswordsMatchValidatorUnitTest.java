package com.example.mywalkinpal;

import org.junit.Test;
import static org.junit.Assert.*;

public class PasswordsMatchValidatorUnitTest {
    @Test
    public void passwordsMatchValidatorUnitTest_MatchingPasswords_ReturnsTrue() {
        assertTrue((SignUpActivity.validatePasswordsMatch("PasswordConf", "PasswordConf")== true));
    }

    @Test
    public void passwordsMatchValidatorUnitTest_SimilarButNotMatchingPasswords_ReturnsFalse() {
        assertTrue((SignUpActivity.validatePasswordsMatch("PasswordConf", "Passwordconf")== false));
    }

    @Test
    public void passwordsMatchValidatorUnitTest_NotMatchingPasswords_ReturnsFalse() {
        assertTrue((SignUpActivity.validatePasswordsMatch("PasswordConf", "")== false));
    }
}

