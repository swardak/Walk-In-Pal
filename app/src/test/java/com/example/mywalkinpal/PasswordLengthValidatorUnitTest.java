package com.example.mywalkinpal;

import org.junit.Test;
import static org.junit.Assert.*;

public class PasswordLengthValidatorUnitTest {
    @Test
    public void passwordLengthValidatorUnitTest_PasswordCorrectLength_ReturnsTrue() {
        assertTrue(SignUpActivity.validatePasswordLength("PasswordConf"));
    }

    @Test
    public void passwordLengthValidatorUnitTest_PasswordTooShort_ReturnsFalse() {
        assertFalse(SignUpActivity.validatePasswordLength("pass"));
    }

    @Test
    public void passwordLengthValidatorUnitTest_PasswordNull_ReturnsFalse() {
        assertTrue(SignUpActivity.validatePasswordLength(""));
    }
}
