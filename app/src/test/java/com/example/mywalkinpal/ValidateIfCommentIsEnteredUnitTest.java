package com.example.mywalkinpal;

import org.junit.Test;
import static org.junit.Assert.*;

public class ValidateIfCommentIsEnteredUnitTest {
    @Test
    public void ValidateIfCommentIsEnteredUnitTest_NotEmptyField_ReturnsTrue() {
        assert(RateClinicActivity.validateIfCommentIsEmpty("Great service!"));
    }

    @Test
    public void ValidateIfCommentIsEnteredUnitTest_EmptyField_ReturnsFalse() {
        assertFalse(RateClinicActivity.validateIfCommentIsEmpty(""));
    }
}
