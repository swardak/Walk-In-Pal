package com.example.mywalkinpal;

import org.junit.Test;
import static org.junit.Assert.*;

public class PasswordHashedValidatorUnitTest {
    @Test
    public void passwordHashedValidatorUnitTest_HashedPassSimple_isTrue() {
        assertSame("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", SignUpActivity.sha256("password") );

    }

    @Test
    public void passwordHashedValidatorUnitTest_HashedPassComplex_isTrue() {
        assertTrue((SignUpActivity.sha256("sz8=xdVpPb@cr3_Z")) == ("27550e07c0510cfedc771a077eaba50123c2f7a56aaaa21c4d2dd9862ae17f76"));

    }

    @Test
    public void passwordHashedValidatorUnitTest_HashedPassSimple_isFalse() {
        assertTrue((SignUpActivity.sha256("password")) != ("5e884898da28057151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8"));
    }

    @Test
    public void passwordHashedValidatorUnitTest_HashedPassComplex_isFalse() {
        assertTrue((SignUpActivity.sha256("sz8=xdVpPb@cr3_Z")) != ("27550e07c0510cfedc771a077eaba50123c2f7a56aaaa21c4d2dd9862ab17f76"));
    }

}
