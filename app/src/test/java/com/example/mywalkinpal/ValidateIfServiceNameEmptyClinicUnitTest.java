package com.example.mywalkinpal;

import org.junit.Test;
import static org.junit.Assert.*;

public class ValidateIfServiceNameEmptyClinicUnitTest {
    @Test
    public void ValidateIfServiceNameEmptyUnitTest_NotEmptyField_ReturnsTrue() {
        assert(ModifyServiceActivity.validateEmptyServiceName("injection"));
    }

    @Test
    public void ValidateIfServiceNameEmptyUnitTest_EmptyField_ReturnsFalse() {
        assertFalse(ModifyServiceActivity.validateEmptyServiceName(""));
    }
}