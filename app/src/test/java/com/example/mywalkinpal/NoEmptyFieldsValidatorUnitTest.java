package com.example.mywalkinpal;

import org.junit.Test;
import static org.junit.Assert.*;

public class NoEmptyFieldsValidatorUnitTest {
    @Test
    public void noEmptyFieldsValidatorUnitTest_NoEmptyFields_ReturnsTrue() {
        assertTrue((SignUpActivity.validateNoEmptyFields("name", "last", "email@email.com", "passwordconf",
                "passwordconf")== true));
    }

    @Test
    public void noEmptyFieldsValidatorUnitTest_OneEmptyField_ReturnsFalse() {
        assertTrue((SignUpActivity.validateNoEmptyFields
                ("name", "last", "email@email.com", "",
                        "passwordconf") == false));
    }

    @Test
    public void noEmptyFieldsValidatorUnitTest_AllEmptyFields_ReturnsFalse() {
        assertTrue((SignUpActivity.validateNoEmptyFields("", "", "",
                "", "")== false));
    }
}
