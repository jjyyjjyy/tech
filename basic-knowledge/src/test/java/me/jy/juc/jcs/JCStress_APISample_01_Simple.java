package me.jy.juc.jcs;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;

/**
 * @author jy
 */
@JCStressTest
@Outcome(id = "1, 1", expect = Expect.ACCEPTABLE_INTERESTING, desc = "Both actors came up with the same value: atomicity failure.")
@Outcome(id = "1, 2", expect = Expect.ACCEPTABLE, desc = "actor1 -> actor2")
@Outcome(id = "2, 1", expect = Expect.ACCEPTABLE, desc = "actor2 -> actor1")
@State
public class JCStress_APISample_01_Simple {

    int v;

    @Actor
    public void actor1(II_Result result) {
        result.r1 = ++v;
    }

    @Actor
    public void actor2(II_Result result) {
        result.r2 = ++v;
    }
}
