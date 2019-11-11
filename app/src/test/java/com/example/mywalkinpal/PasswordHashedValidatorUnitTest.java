package com.example.mywalkinpal;

import org.junit.Test;
import static org.junit.Assert.*;

public class PasswordHashedValidatorUnitTest {
    @Test
    public void passwordHashedValidatorUnitTest_HashedPassSimple_isTrue() {
        String passwordToHash = SignUpActivity.sha256("password");
        String hashedPassword = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
        assertEquals(hashedPassword, passwordToHash);


    }

    @Test
    public void passwordHashedValidatorUnitTest_HashedPassComplex_isTrue() {

        String passwordToHash = SignUpActivity.sha256("sz8=xdVpPb@cr3_Z");
        String hashedPassword = "27550e07c0510cfedc771a077eaba50123c2f7a56aaaa21c4d2dd9862ae17f76";
        assertEquals(hashedPassword, passwordToHash);

    }

    @Test
    public void passwordHashedValidatorUnitTest_HashedPassSimple_isFalse() {
        String passwordToHash = SignUpActivity.sha256("password");
        String hashedPassword = "5e884898da28047151d0e56f8dc6292773603d0d6aadbdd62a11ef721d1542d8";
        assertNotEquals(hashedPassword, passwordToHash);
    }

    @Test
    public void passwordHashedValidatorUnitTest_HashedPassComplex_isFalse() {

        String passwordToHash = SignUpActivity.sha256("sz8=xdVpPb@cr3_Z");
        String hashedPassword = "27550e07c0510cfedc771a077eaba51123c2f7a56aaaa21c4d2dd9862ae17f76";
        assertNotEquals(hashedPassword, passwordToHash);
    }

}
