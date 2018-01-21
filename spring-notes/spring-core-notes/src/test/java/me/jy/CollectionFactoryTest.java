package me.jy;

import org.junit.Test;
import org.springframework.core.CollectionFactory;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author jy
 * @date 2018/01/21
 */
public class CollectionFactoryTest {

    @Test
    public void testCreateCollection() {
        // ArrayList default
        Collection<Object> collection = CollectionFactory.createCollection(List.class, 1);
        assertTrue(collection.getClass().equals(ArrayList.class));


        // LinkedHashMap default
        Map<Object, Object> map = CollectionFactory.createMap(Map.class, 1);
        assertEquals(LinkedHashMap.class, map.getClass());

        //value -> type string
        Properties properties = CollectionFactory.createStringAdaptingProperties();
        properties.put("a", 1);
        assertEquals("1", properties.getProperty("a"));
    }
}
