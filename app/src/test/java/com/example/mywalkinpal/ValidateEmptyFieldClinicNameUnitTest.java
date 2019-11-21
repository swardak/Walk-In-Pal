package com.example.mywalkinpal;

import org.junit.Test;
import static org.junit.Assert.*;

public class ValidateEmptyFieldClinicNameUnitTest {

    @Test
    public void ValidateEmptyFieldClinicNameUnitTest_NotEmptyField_ReturnsTrue() {
        assert(ManageProfileActivity.validateEmptyName("Apple Tree Clinic"));
    }

    @Test
    public void ValidateEmptyFieldClinicNameUnitTest_EmptyField_ReturnsFalse() {
        assertFalse(ManageProfileActivity.validateEmptyName(""));
    }
}
