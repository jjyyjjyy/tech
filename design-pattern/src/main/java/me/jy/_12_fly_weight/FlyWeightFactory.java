package me.jy._12_fly_weight;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jy
 */
public class FlyWeightFactory {

    private final Map<String, FlyWeight> flyWeights = new ConcurrentHashMap<>();

    public FlyWeight get(String key) {
        return flyWeights.putIfAbsent(key, new ConcreteFlyWeight(key));
    }
}
