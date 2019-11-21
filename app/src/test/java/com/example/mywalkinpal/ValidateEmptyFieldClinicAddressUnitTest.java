package com.example.mywalkinpal;

import org.junit.Test;
import static org.junit.Assert.*;

public class ValidateEmptyFieldClinicAddressUnitTest {
    @Test
    public void ValidateEmptyFieldClinicNameUnitTest_NotEmptyField_ReturnsTrue() {
        assert(ManageProfileActivity.validateEmptyAddress("123 iris road"));
    }

    @Test
    public void ValidateEmptyFieldClinicNameUnitTest_EmptyField_ReturnsFalse() {
        assertFalse(ManageProfileActivity.validateEmptyAddress(""));
    }
}
