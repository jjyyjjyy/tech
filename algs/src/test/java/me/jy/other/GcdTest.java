package me.jy.other;

import me.jy.algs4.ch1.Gcd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GcdTest {

    @Test
    void gcd() {
        Assertions.assertEquals(1, Gcd.gcd(1111111, 1234567));
    }
}
