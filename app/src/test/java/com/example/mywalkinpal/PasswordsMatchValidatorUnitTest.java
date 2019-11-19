package com.example.mywalkinpal;

import org.junit.Test;
import static org.junit.Assert.*;

public class PasswordsMatchValidatorUnitTest {
    @Test
    public void passwordsMatchValidatorUnitTest_MatchingPasswords_ReturnsTrue() {
        assertTrue(SignUpActivity.validatePasswordsMatch("PasswordConf", "PasswordConf"));
    }

    @Test
    public void passwordsMatchValidatorUnitTest_SimilarButNotMatchingPasswords_ReturnsFalse() {
        assertFalse(SignUpActivity.validatePasswordsMatch("PasswordConf", "Passwordconf"));
    }

    @Test
    public void passwordsMatchValidatorUnitTest_NotMatchingPasswords_ReturnsFalse() {
        assertFalse(SignUpActivity.validatePasswordsMatch("PasswordConf", ""));
    }
}

