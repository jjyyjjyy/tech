package me.jy.algs4.ch1;

import java.util.Arrays;

/**
 * @author jy
 */
public class Rational {

    private final long numerator;

    private final long denominator;

    public Rational(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static Rational valueOf(String source) {
        long[] longs = Arrays.stream(source.split(",")).mapToLong(Long::valueOf).toArray();
        return new Rational(longs[0], longs[1]);
    }

    public Rational plus(Rational rational) {

        long a = this.denominator;
        long b = rational.getDenominator();
        long lcm = getLCM(a, b);

        long n1 = lcm / a * numerator;
        long n2 = lcm / b * rational.getNumerator();

        return new Rational(n1 + n2, lcm).simplify();

    }

    public Rational minus(Rational rational) {
        long a = this.denominator;
        long b = rational.getDenominator();
        long lcm = getLCM(a, b);

        long n1 = lcm / a * numerator;
        long n2 = lcm / b * rational.getNumerator();

        return new Rational(n1 - n2, lcm).simplify();
    }

    public Rational multi(Rational rational) {
        return new Rational(this.numerator * rational.getNumerator(), this.denominator * rational.getDenominator()).simplify();
    }

    public Rational divides(Rational rational) {
        return new Rational(this.numerator * rational.getDenominator(), this.denominator * rational.getNumerator()).simplify();
    }

    private Rational simplify() {
        long gcd;
        if ((gcd = Gcd.gcd(numerator, denominator)) == 1) {
            return this;
        }
        return new Rational(numerator / gcd, denominator / gcd);
    }

    private static long getLCM(long a, long b) {
        return a * b / Gcd.gcd(a, b);
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Rational)) {
            return false;
        }
        Rational another = (Rational) obj;
        return another.getNumerator() == this.getNumerator()
            && another.getDenominator() == this.getDenominator();
    }

    @Override
    public String toString() {
        return "Rational{" +
            "numerator=" + numerator +
            ", denominator=" + denominator +
            '}';
    }

    public long getNumerator() {
        return numerator;
    }

    public long getDenominator() {
        return denominator;
    }
}
