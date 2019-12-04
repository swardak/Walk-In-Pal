package com.example.mywalkinpal;

import org.junit.Test;
import static org.junit.Assert.*;

public class ValidateIfRoleNameEmptyClinicUnitTest {
    @Test
    public void ValidateIfRoleNameEmptyClinicUnitTest_NotEmptyField_ReturnsTrue() {
        assert(ModifyServiceActivity.validateEmptyRoleName("doctor"));
    }

    @Test
    public void ValidateIfRoleNameEmptyClinicUnitTest_EmptyField_ReturnsFalse() {
        assertFalse(ModifyServiceActivity.validateEmptyRoleName(""));
    }
}