package com.example.mywalkinpal;

import org.junit.Test;
import static org.junit.Assert.*;

public class NoEmptyServicesRolesValidatorUnitTest {
    @Test
    public void noEmptyServicesRolesValidatorUnitTest_NoEmptyFields_ReturnsTrue() {
        assert(AddServiceActivity.validateEmptyField("Injection", "Nurse"));
    }

    @Test
    public void noEmptyServicesRolesValidatorUnitTest_OneEmptyField_ReturnsFalse() {
        assertFalse(AddServiceActivity.validateEmptyField("", "Nurse"));
    }

    @Test
    public void noEmptyServicesRolesValidatorUnitTest_AllEmptyFields_ReturnsFalse() {
        assertFalse(AddServiceActivity.validateEmptyField("", ""));
    }
}
