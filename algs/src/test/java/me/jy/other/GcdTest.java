package me.jy.other;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GcdTest {

    private Gcd gcd = new Gcd();

    @Test
    void gcd() {
        Assertions.assertEquals(1, gcd.gcd(1111111, 1234567));
    }
}
