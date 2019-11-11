package com.example.mywalkinpal;

import org.junit.Test;
import static org.junit.Assert.*;

public class NoEmptyFieldsValidatorUnitTest {
    @Test
    public void noEmptyFieldsValidatorUnitTest_NoEmptyFields_ReturnsTrue() {
        assert(SignUpActivity.validateNoEmptyFields("name", "last", "email@email.com", "passwordconf",
                "passwordconf"));
    }

    @Test
    public void noEmptyFieldsValidatorUnitTest_OneEmptyField_ReturnsFalse() {
        assertFalse(SignUpActivity.validateNoEmptyFields("name", "last",
                "email@email.com", "", "passwordconf"));
    }

    @Test
    public void noEmptyFieldsValidatorUnitTest_AllEmptyFields_ReturnsFalse() {
        assertFalse(SignUpActivity.validateNoEmptyFields("", "", "",
                "", ""));
    }
}
