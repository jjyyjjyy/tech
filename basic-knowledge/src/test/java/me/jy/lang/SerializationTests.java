package me.jy.lang;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author jy
 * @date 2017/11/07
 */
public class SerializationTests implements Serializable {

    private static final long serialVersionUID = 989148841462074401L;

    @Test
    public void testSerialize() throws Exception {
        Shaker shaker = new Shaker();
        shaker.setName("wow");

        String fileName = "shake.dat";

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
        outputStream.writeObject(shaker);
        outputStream.flush();
        outputStream.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
//        assertEquals("wow", Shaker.class.cast(ois.readObject()).getName());

        new File(fileName).delete();

        ois.close();
    }

    public static class Shaker implements Serializable {

        private static final long serialVersionUID = 1L;

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Shaker{" + "name='" + name + "'}";
        }

        private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
            // called when deserialize
//            throw new InvalidObjectException("readObject - can't deserialize Shaker");
        }

        private void readObjectNoData() throws ObjectStreamException {
            throw new InvalidObjectException("readObjectNoData - can't deserialize Shaker");
        }
    }
}
