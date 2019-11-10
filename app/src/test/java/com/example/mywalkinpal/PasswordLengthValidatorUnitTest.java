package com.example.mywalkinpal;

import org.junit.Test;
import static org.junit.Assert.*;

public class PasswordLengthValidatorUnitTest {
    @Test
    public void passwordLengthValidatorUnitTest_PasswordCorrectLength_ReturnsTrue() {
        assertTrue((SignUpActivity.validatePasswordLength("PasswordConf")== true));
    }

    @Test
    public void passwordLengthValidatorUnitTest_PasswordTooShort_ReturnsFalse() {
        assertTrue((SignUpActivity.validatePasswordLength("pass")== false));
    }

    @Test
    public void passwordLengthValidatorUnitTest_PasswordNull_ReturnsFalse() {
        assertTrue((SignUpActivity.validatePasswordLength("")== false));
    }
}
